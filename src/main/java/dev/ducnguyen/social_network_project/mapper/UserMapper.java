package dev.ducnguyen.social_network_project.mapper;

import dev.ducnguyen.social_network_project.dto.request.UserCreateRequest;
import dev.ducnguyen.social_network_project.dto.request.UserUpdateRequest;
import dev.ducnguyen.social_network_project.dto.response.UserResponse;
import dev.ducnguyen.social_network_project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
