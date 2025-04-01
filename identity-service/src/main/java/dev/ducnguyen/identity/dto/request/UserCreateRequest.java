package dev.ducnguyen.identity.dto.request;

import dev.ducnguyen.identity.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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

    String avatar;
    String bio;
    String location;
    LocalDate dob;
}
