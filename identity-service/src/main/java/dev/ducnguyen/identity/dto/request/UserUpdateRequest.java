package dev.ducnguyen.identity.dto.request;

import dev.ducnguyen.identity.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstName;
    String surName;
    Gender gender;
    String profilePicture;
    String bio;
}
