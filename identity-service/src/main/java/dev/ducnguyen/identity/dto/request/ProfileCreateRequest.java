package dev.ducnguyen.identity.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileCreateRequest {
    String userId;
    @NotEmpty(message = "FIRSTNAME_EMPTY")
    String firstName;

    @NotEmpty(message = "SURNAME_EMPTY")
    String surName;

    String avatar;
    String bio;
    String location;
    LocalDate dob;
}
