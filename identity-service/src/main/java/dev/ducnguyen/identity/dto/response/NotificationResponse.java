package dev.ducnguyen.identity.dto.response;

import dev.ducnguyen.identity.enums.NotificationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationResponse {
    NotificationType type;
    String content;
    boolean isRead;
    UserCustomResponse user;
    LocalDateTime createdAt;
}
