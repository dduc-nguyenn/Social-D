package dev.ducnguyen.identity.dto.request;

import dev.ducnguyen.identity.entity.User;
import dev.ducnguyen.identity.enums.FriendshipStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendshipRequest {
    FriendshipStatus status;
    User user1;
    User user2;
}
