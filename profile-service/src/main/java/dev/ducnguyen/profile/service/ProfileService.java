package dev.ducnguyen.profile.service;

import dev.ducnguyen.profile.dto.request.ProfileCreateRequest;
import dev.ducnguyen.profile.dto.response.ProfileResponse;
import dev.ducnguyen.profile.entity.Profile;
import dev.ducnguyen.profile.exception.AppException;
import dev.ducnguyen.profile.exception.ErrorCode;
import dev.ducnguyen.profile.mapper.ProfileMapper;
import dev.ducnguyen.profile.repository.ProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @PreAuthorize("hasRole('ADMIN')")
    public List<ProfileResponse> getAllProfile() {
        return profileRepository.findAll().stream()
                .map(profileMapper::toProfileResponse)
                .toList();
    }

    public ProfileResponse getProfileById(String id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));

        return profileMapper.toProfileResponse(profile);
    }

    public ProfileResponse getProfileByUserId(String userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));

        return profileMapper.toProfileResponse(profile);
    }

    public void deleteProfileById(String id) {
        profileRepository.deleteById(id);
    }
}
