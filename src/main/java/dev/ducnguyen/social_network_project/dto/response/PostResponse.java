package dev.ducnguyen.social_network_project.dto.response;

import dev.ducnguyen.social_network_project.enums.Privacy;
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
