package dev.ducnguyen.social_network_project.dto.request;

import dev.ducnguyen.social_network_project.enums.Gender;
import dev.ducnguyen.social_network_project.validator.GenderValid;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @Size(min = 1, max = 50)
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    @Email(message = "EMAIL_INVALID")
    String email;

    @NotEmpty(message = "FIRSTNAME_EMPTY")
    String firstName;

    @NotEmpty(message = "SURNAME_EMPTY")
    String surName;

    Gender gender;
    String profilePicture;
    String bio;
}
