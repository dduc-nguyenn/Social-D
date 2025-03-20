package dev.ducnguyen.social_network_project.service;

import dev.ducnguyen.social_network_project.constant.RoleConstant;
import dev.ducnguyen.social_network_project.dto.request.UserCreateRequest;
import dev.ducnguyen.social_network_project.dto.request.UserUpdateRequest;
import dev.ducnguyen.social_network_project.dto.response.UserResponse;
import dev.ducnguyen.social_network_project.entity.Role;
import dev.ducnguyen.social_network_project.entity.User;
import dev.ducnguyen.social_network_project.exception.AppException;
import dev.ducnguyen.social_network_project.exception.ErrorCode;
import dev.ducnguyen.social_network_project.mapper.UserMapper;
import dev.ducnguyen.social_network_project.repository.RoleRepository;
import dev.ducnguyen.social_network_project.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreateRequest request) {

        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        if (userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);

        if (request.getGender() == null)
            throw new AppException(ErrorCode.GENDER_EMPTY);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(RoleConstant.USER_ROLE).ifPresent(roles::add);
        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse getMyInfoUser(){
        var context = SecurityContextHolder.getContext();

        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }

    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(
                userRepository
                        .findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND))
        );
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}

