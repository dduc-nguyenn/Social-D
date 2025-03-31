package dev.ducnguyen.identity.service;

import dev.ducnguyen.identity.dto.request.PostCreateRequest;
import dev.ducnguyen.identity.dto.response.PostResponse;
import dev.ducnguyen.identity.entity.Friendship;
import dev.ducnguyen.identity.entity.Post;
import dev.ducnguyen.identity.entity.User;
import dev.ducnguyen.identity.enums.FriendshipStatus;
import dev.ducnguyen.identity.exception.AppException;
import dev.ducnguyen.identity.exception.ErrorCode;
import dev.ducnguyen.identity.mapper.PostMapper;
import dev.ducnguyen.identity.repository.FriendshipRepository;
import dev.ducnguyen.identity.repository.PostRepository;
import dev.ducnguyen.identity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    FriendshipRepository friendshipRepository;
    PostMapper postMapper;
    UserService userService;

    public PostResponse createPost(PostCreateRequest request) {
        Post post = postMapper.toPost(request);

        post.setUser(userService.getCurrentUser());

        return postMapper.toPostResponse(postRepository.save(post));
    }

    public List<PostResponse> getMyPosts() {
        return postRepository.findByUserId(userService.getCurrentUser().getId()).stream()
                .map(postMapper::toPostResponse)
                .toList();
    }

    public List<PostResponse> getNewPosts() {
        List<Friendship> friendships1 = friendshipRepository.findByUser1AndStatus
                (userService.getCurrentUser(), FriendshipStatus.ACCEPTED);

        List<Friendship> friendships2 = friendshipRepository.findByUser2AndStatus
                (userService.getCurrentUser(), FriendshipStatus.ACCEPTED);

        List<User> friends = new ArrayList<>();

        for (Friendship f : friendships1) {
            friends.add(f.getUser2());
        }

        for (Friendship f : friendships2) {
            friends.add(f.getUser1());
        }

//        for (User friend : friends) {
//            log.info("Bạn bè: {}", friend.toString());
//            log.info("Tên bạn bè: {}", friend.getUsername());
//        }

        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);

        return postRepository.findByUserInAndCreatedAtAfter(friends, twentyFourHoursAgo).stream()
                .map(postMapper::toPostResponse).toList();
    }

    public PostResponse getPostById(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND));

        return postMapper.toPostResponse(post);
    }

    public void deletePost(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND));

        if (!userService.getCurrentUser().getId().equals(post.getUser().getId())) {
            throw new AppException(ErrorCode.DELETE_WRONG_POST);
        }

        postRepository.deleteById(postId);
    }
}
