package dev.ducnguyen.identity.service;

import dev.ducnguyen.identity.constant.RoleConstant;
import dev.ducnguyen.identity.dto.request.UserCreateRequest;
import dev.ducnguyen.identity.dto.request.UserUpdateRequest;
import dev.ducnguyen.identity.dto.response.UserResponse;
import dev.ducnguyen.identity.entity.Role;
import dev.ducnguyen.identity.entity.User;
import dev.ducnguyen.identity.exception.AppException;
import dev.ducnguyen.identity.exception.ErrorCode;
import dev.ducnguyen.identity.mapper.ProfileMapper;
import dev.ducnguyen.identity.mapper.UserMapper;
import dev.ducnguyen.identity.repository.RoleRepository;
import dev.ducnguyen.identity.repository.UserRepository;
import dev.ducnguyen.identity.repository.httpclient.ProfileClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    ProfileClient profileClient;
    ProfileMapper profileMapper;

    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        if (userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(RoleConstant.USER_ROLE).ifPresent(roles::add);
        user.setRoles(roles);

        user = userRepository.save(user);

        var profileRequest = profileMapper.toProfileCreateRequest(request);
        profileRequest.setUserId(user.getId());
        log.info("User ID: {}", user.getId());
        profileClient.createProfile(profileRequest);

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse getMyInfoUser(){
        return userMapper.toUserResponse(getCurrentUser());
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

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }
}

