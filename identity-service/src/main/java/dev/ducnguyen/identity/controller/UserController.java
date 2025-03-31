package dev.ducnguyen.identity.controller;

import dev.ducnguyen.identity.dto.request.UserCreateRequest;
import dev.ducnguyen.identity.dto.request.UserUpdateRequest;
import dev.ducnguyen.identity.dto.response.ApiResponse;
import dev.ducnguyen.identity.dto.response.UserResponse;
import dev.ducnguyen.identity.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> create(@RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getAll() {
        var user = SecurityContextHolder.getContext().getAuthentication();
        log.info("User: {}", user.getName());

        user.getAuthorities().forEach(
                authority -> log.info("Authority: {}", authority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .data(userService.getAllUsers())
                .build();
    }

    @GetMapping("/my-info")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .data(userService.getMyInfoUser())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getById(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.getUserById(userId))
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> update(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> delete(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return ApiResponse.<String>builder()
                .data("User has been deleted!")
                .build();
    }
}
