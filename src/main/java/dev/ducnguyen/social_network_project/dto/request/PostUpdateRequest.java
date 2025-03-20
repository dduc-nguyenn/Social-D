package dev.ducnguyen.social_network_project.dto.request;

import dev.ducnguyen.social_network_project.enums.Privacy;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostUpdateRequest {
    String content;
    String mediaUrl;
    Privacy privacy;
}
