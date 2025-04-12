package dev.ducnguyen.profile.controller;

import dev.ducnguyen.profile.dto.ApiResponse;
import dev.ducnguyen.profile.dto.request.ProfileCreateRequest;
import dev.ducnguyen.profile.dto.response.ProfileResponse;
import dev.ducnguyen.profile.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalProfileController {
    ProfileService profileService;

    @PostMapping
    ApiResponse<ProfileResponse> create(@RequestBody ProfileCreateRequest request) {
        return ApiResponse.<ProfileResponse>builder()
                .data(profileService.createProfile(request))
                .build();
    }

    @DeleteMapping("/{profileId}")
    ApiResponse<String> delete(@PathVariable String profileId) {
        profileService.deleteProfileById(profileId);
        return ApiResponse.<String>builder()
                .data("Profile deleted!")
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<ProfileResponse> getProfile(@PathVariable String userId) {
        return ApiResponse.<ProfileResponse>builder()
                .data(profileService.getProfileByUserId(userId))
                .build();
    }

}
