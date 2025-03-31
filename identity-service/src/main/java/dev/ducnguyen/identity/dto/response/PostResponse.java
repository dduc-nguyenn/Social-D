package dev.ducnguyen.identity.dto.response;

import dev.ducnguyen.identity.enums.Privacy;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
    String id;
    String content;
    String mediaUrl;
    Privacy privacy;
    LocalDateTime createdAt;
}
