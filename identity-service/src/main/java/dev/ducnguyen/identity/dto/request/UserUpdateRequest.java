package dev.ducnguyen.identity.dto.request;

import dev.ducnguyen.identity.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstName;
    String surName;
    String avatar;
    String bio;
    String location;
    LocalDate dob;
}
