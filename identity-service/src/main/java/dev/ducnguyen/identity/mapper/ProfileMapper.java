package dev.ducnguyen.identity.mapper;

import dev.ducnguyen.identity.dto.request.ProfileCreateRequest;
import dev.ducnguyen.identity.dto.request.UserCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreateRequest toProfileCreateRequest(UserCreateRequest request);
}
