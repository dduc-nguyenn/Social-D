package dev.ducnguyen.profile.controller;

import dev.ducnguyen.profile.dto.request.ProfileCreateRequest;
import dev.ducnguyen.profile.dto.response.ProfileResponse;
import dev.ducnguyen.profile.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileController {
    ProfileService profileService;

    @PostMapping
    ProfileResponse create(@RequestBody ProfileCreateRequest request) {
        return profileService.createProfile(request);
    }

    @GetMapping("/{profileId}")
    ProfileResponse getById(@PathVariable String profileId) {
        return profileService.getProfileById(profileId);
    }
}
