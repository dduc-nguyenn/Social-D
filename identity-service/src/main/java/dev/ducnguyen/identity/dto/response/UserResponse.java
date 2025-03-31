package dev.ducnguyen.identity.dto.response;

import dev.ducnguyen.identity.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String email;
    String firstName;
    String surName;
    Gender gender;
    String profilePicture;
    String bio;
    LocalDateTime createdAt;

    List<PostResponse> posts;
    List<NotificationResponse> notifications;
//    List<FriendshipResponse> friendship1;
//    List<FriendshipResponse> friendship2;
}
