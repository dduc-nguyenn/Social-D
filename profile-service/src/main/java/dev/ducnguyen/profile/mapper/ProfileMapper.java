package dev.ducnguyen.profile.mapper;

import dev.ducnguyen.profile.dto.request.ProfileCreateRequest;
import dev.ducnguyen.profile.dto.response.ProfileResponse;
import dev.ducnguyen.profile.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toProfile(ProfileCreateRequest request);
    ProfileResponse toProfileResponse(Profile profile);
}
