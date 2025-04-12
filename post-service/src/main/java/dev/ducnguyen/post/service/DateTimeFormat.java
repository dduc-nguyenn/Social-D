package dev.ducnguyen.post.service;

import dev.ducnguyen.post.exception.AppException;
import dev.ducnguyen.post.exception.ErrorCode;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class DateTimeFormat {

    Map<Long, Function<Instant, String>> strategyMap = new LinkedHashMap<>();

    public DateTimeFormat() {
        strategyMap.put(60L, this::formatInSeconds);
        strategyMap.put(3600L, this::formatInMinutes);
        strategyMap.put(86400L, this::formatInHours);
        strategyMap.put(Long.MAX_VALUE, this::formatInDate);
    }

    public String format(Instant instant) {
        long elapsedSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());

        var strategy = strategyMap.entrySet()
                .stream()
                .filter(longFunctionEntry -> elapsedSeconds < longFunctionEntry.getKey())
                .findFirst()
                .orElseThrow(() -> new AppException(ErrorCode.STRATEGY_NOT_FOUND));

        return strategy.getValue().apply(instant);
    }

    private String formatInSeconds(Instant instant) {
        long elapsedSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());
        return elapsedSeconds + " seconds ago";
    }

    private String formatInMinutes(Instant instant) {
        long elapsedSeconds = ChronoUnit.MINUTES.between(instant, Instant.now());
        return elapsedSeconds + " minutes ago";
    }

    private String formatInHours(Instant instant) {
        long elapsedSeconds = ChronoUnit.HOURS.between(instant, Instant.now());
        return elapsedSeconds + " hours ago";
    }

    private String formatInDate(Instant instant) {
        LocalDateTime localDateTime = instant.atZone(ZoneId.of("UTC")).toLocalDateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        return localDateTime.format(dateTimeFormatter);
    }
}
