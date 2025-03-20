package dev.ducnguyen.social_network_project.service;

import dev.ducnguyen.social_network_project.dto.request.PostCreateRequest;
import dev.ducnguyen.social_network_project.dto.response.PostResponse;
import dev.ducnguyen.social_network_project.entity.Post;
import dev.ducnguyen.social_network_project.entity.User;
import dev.ducnguyen.social_network_project.exception.AppException;
import dev.ducnguyen.social_network_project.exception.ErrorCode;
import dev.ducnguyen.social_network_project.mapper.PostMapper;
import dev.ducnguyen.social_network_project.repository.PostRepository;
import dev.ducnguyen.social_network_project.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    PostMapper postMapper;

    public PostResponse createPost(PostCreateRequest request) {
        Post post = postMapper.toPost(request);

        User user = userRepository.findByUsername(getContextUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        post.setUser(user);

        return postMapper.toPostResponse(postRepository.save(post));
    }

    public List<PostResponse> getAllPosts() {

        User user = userRepository.findByUsername(getContextUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return postRepository.findByUserId(user.getId()).stream()
                .map(postMapper::toPostResponse)
                .toList();
    }


    public String getContextUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
