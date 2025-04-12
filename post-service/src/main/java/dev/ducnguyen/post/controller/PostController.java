package dev.ducnguyen.post.controller;

import dev.ducnguyen.post.dto.ApiResponse;
import dev.ducnguyen.post.dto.PageResponse;
import dev.ducnguyen.post.dto.request.PostRequest;
import dev.ducnguyen.post.dto.response.PostResponse;
import dev.ducnguyen.post.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @PostMapping("/create")
    ApiResponse<PostResponse> create(@RequestBody PostRequest request) {
        return ApiResponse.<PostResponse>builder()
                .data(postService.createPost(request))
                .build();
    }

    @GetMapping("/my-posts")
    ApiResponse<PageResponse<PostResponse>> getMy(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {

        return ApiResponse.<PageResponse<PostResponse>>builder()
                .data(postService.getMyPosts(page, size))
                .build();
    }
}
