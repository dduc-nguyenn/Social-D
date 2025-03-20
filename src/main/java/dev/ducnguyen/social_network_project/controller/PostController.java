package dev.ducnguyen.social_network_project.controller;

import dev.ducnguyen.social_network_project.dto.request.PostCreateRequest;
import dev.ducnguyen.social_network_project.dto.response.ApiResponse;
import dev.ducnguyen.social_network_project.dto.response.PostResponse;
import dev.ducnguyen.social_network_project.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @GetMapping
    ApiResponse<List<PostResponse>> getAll() {
        return ApiResponse.<List<PostResponse>>builder()
                .data(postService.getAllPosts())
                .build();
    }

    @PostMapping
    ApiResponse<PostResponse> create(@RequestBody PostCreateRequest request) {
        return ApiResponse.<PostResponse>builder()
                .data(postService.createPost(request))
                .build();
    }
}
