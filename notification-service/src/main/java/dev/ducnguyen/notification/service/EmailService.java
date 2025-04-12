package dev.ducnguyen.notification.service;

import dev.ducnguyen.notification.dto.request.EmailRequest;
import dev.ducnguyen.notification.dto.request.SendEmailRequest;
import dev.ducnguyen.notification.dto.request.Sender;
import dev.ducnguyen.notification.dto.response.EmailResponse;
import dev.ducnguyen.notification.exception.AppException;
import dev.ducnguyen.notification.exception.ErrorCode;
import dev.ducnguyen.notification.repository.httpclient.EmailClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailClient emailClient;

    @Value("${notification.email.brevo-apikey}")
    @NonFinal
    String apiKey;

    public EmailResponse sendEmail(SendEmailRequest request) {
        Sender sender = Sender.builder()
                .email("dustinducnguyen.vn@gmail.com")
                .name("Dustin Nguyen")
                .build();

        EmailRequest emailRequest = EmailRequest.builder()
                .sender(sender)
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();

        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        }
        catch (FeignException e) {
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}
