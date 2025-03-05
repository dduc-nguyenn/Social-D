package dev.ducnguyen.social_network_project.dto.response;

import dev.ducnguyen.social_network_project.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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
}
