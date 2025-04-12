package dev.ducnguyen.post.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest {
    String userId;
    String content;
    String imageUrl;
    Instant createdDate;
    Instant modifiedDate;
}
