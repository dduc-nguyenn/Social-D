package dev.ducnguyen.profile.controller;

import dev.ducnguyen.profile.dto.ApiResponse;
import dev.ducnguyen.profile.dto.response.ProfileResponse;
import dev.ducnguyen.profile.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileController {
    ProfileService profileService;

    @GetMapping("/{id}")
    ApiResponse<ProfileResponse> getById(@PathVariable String id) {
        return ApiResponse.<ProfileResponse>builder()
                .data(profileService.getProfileById(id))
                .build();
    }

    @GetMapping
    ApiResponse<List<ProfileResponse>> getList() {
        return ApiResponse.<List<ProfileResponse>>builder()
                .data(profileService.getAllProfile())
                .build();
    }
}
