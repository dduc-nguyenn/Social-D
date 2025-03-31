package dev.ducnguyen.identity.dto.response;

import dev.ducnguyen.identity.enums.FriendshipStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendshipResponse {
    FriendshipStatus status;
    UserCustomResponse user1;
    UserCustomResponse user2;
    LocalDateTime createdAt;
}
