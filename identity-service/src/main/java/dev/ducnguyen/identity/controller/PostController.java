package dev.ducnguyen.identity.controller;

import dev.ducnguyen.identity.dto.request.PostCreateRequest;
import dev.ducnguyen.identity.dto.response.ApiResponse;
import dev.ducnguyen.identity.dto.response.PostResponse;
import dev.ducnguyen.identity.service.PostService;
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
    ApiResponse<List<PostResponse>> getNew() {
        return ApiResponse.<List<PostResponse>>builder()
                .data(postService.getNewPosts())
                .build();
    }

    @GetMapping("/profile")
    ApiResponse<List<PostResponse>> getMy() {
        return ApiResponse.<List<PostResponse>>builder()
                .data(postService.getMyPosts())
                .build();
    }

    @PostMapping
    ApiResponse<PostResponse> create(@RequestBody PostCreateRequest request) {
        return ApiResponse.<PostResponse>builder()
                .data(postService.createPost(request))
                .build();
    }

    @GetMapping("/{postId}")
    ApiResponse<PostResponse> getById(@PathVariable String postId) {
        return ApiResponse.<PostResponse>builder()
                .data(postService.getPostById(postId))
                .build();
    }

    @DeleteMapping("/{postId}")
    ApiResponse<String> delete(@PathVariable String postId) {
        postService.deletePost(postId);
        return ApiResponse.<String>builder()
                .data("Post has been deleted!")
                .build();
    }
}
