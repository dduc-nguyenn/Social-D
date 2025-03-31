package dev.ducnguyen.identity.controller;

import dev.ducnguyen.identity.dto.response.ApiResponse;
import dev.ducnguyen.identity.service.FriendshipService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friendships")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendshipController {
    FriendshipService friendshipService;

    @PostMapping("/{username}")
    ApiResponse<String> addFriend(@PathVariable String username) {
        friendshipService.addFriend(username);

        return ApiResponse.<String>builder()
                .data("Waiting for response!")
                .build();
    }

    @PutMapping("/{username}/{status}")
    ApiResponse<String> changeStatus(@PathVariable String username, @PathVariable String status) {
        friendshipService.changeStatus(username, status);

        return ApiResponse.<String>builder()
                .data("You've been a friend now")
                .build();
    }
}
