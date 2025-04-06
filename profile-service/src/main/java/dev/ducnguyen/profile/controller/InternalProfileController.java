package dev.ducnguyen.profile.controller;

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
    ProfileResponse create(@RequestBody ProfileCreateRequest request) {
        return profileService.createProfile(request);
    }

    @DeleteMapping("/{profileId}")
    void delete(@PathVariable String profileId) {
        profileService.deleteProfileById(profileId);
    }
}
