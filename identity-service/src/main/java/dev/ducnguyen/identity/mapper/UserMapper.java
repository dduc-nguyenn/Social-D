package dev.ducnguyen.identity.mapper;

import dev.ducnguyen.identity.dto.request.UserCreateRequest;
import dev.ducnguyen.identity.dto.request.UserUpdateRequest;
import dev.ducnguyen.identity.dto.response.UserResponse;
import dev.ducnguyen.identity.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
