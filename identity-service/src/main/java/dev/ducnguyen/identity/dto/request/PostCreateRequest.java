package dev.ducnguyen.identity.dto.request;

import dev.ducnguyen.identity.enums.Privacy;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostCreateRequest {
    String content;
    String mediaUrl;
    Privacy privacy;
}
