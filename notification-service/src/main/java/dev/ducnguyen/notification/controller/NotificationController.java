package dev.ducnguyen.notification.controller;

import dev.ducnguyen.event.dto.NotificationEvent;
import dev.ducnguyen.notification.dto.request.Recipient;
import dev.ducnguyen.notification.dto.request.SendEmailRequest;
import dev.ducnguyen.notification.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationController {
    EmailService emailService;

    @KafkaListener(topics = "notification-delivery")
    public void listenNotificationDelivery(NotificationEvent message) {
        log.info("Message received: {}",message);

        Recipient recipient = Recipient.builder()
                .email(message.getRecipient())
                .build();

        emailService.sendEmail(SendEmailRequest.builder()
                .to(recipient)
                .subject(message.getSubject())
                .htmlContent(message.getBody())
                .build());
    }
}
