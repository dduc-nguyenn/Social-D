package dev.ducnguyen.profile.service;

import dev.ducnguyen.profile.dto.request.ProfileCreateRequest;
import dev.ducnguyen.profile.dto.response.ProfileResponse;
import dev.ducnguyen.profile.entity.Profile;
import dev.ducnguyen.profile.mapper.ProfileMapper;
import dev.ducnguyen.profile.repository.ProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileService {
    ProfileRepository profileRepository;
    ProfileMapper profileMapper;

    public ProfileResponse createProfile(ProfileCreateRequest request) {
        Profile profile = profileMapper.toProfile(request);

        return profileMapper.toProfileResponse(profileRepository.save(profile));
    }

    public ProfileResponse getProfileById(String id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Not found profile with id: " + id));

        return profileMapper.toProfileResponse(profile);
    }
}
