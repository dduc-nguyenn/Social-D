package dev.ducnguyen.identity.service;

import dev.ducnguyen.identity.entity.Friendship;
import dev.ducnguyen.identity.entity.Notification;
import dev.ducnguyen.identity.entity.User;
import dev.ducnguyen.identity.enums.FriendshipStatus;
import dev.ducnguyen.identity.enums.NotificationType;
import dev.ducnguyen.identity.exception.AppException;
import dev.ducnguyen.identity.exception.ErrorCode;
import dev.ducnguyen.identity.repository.FriendshipRepository;
import dev.ducnguyen.identity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendshipService {
    FriendshipRepository friendshipRepository;
    UserService userService;
    UserRepository userRepository;

    NotificationService notificationService;

    public void addFriend(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (friendshipRepository.findByUser1AndUser2(userService.getCurrentUser(), user).isPresent()) {
            throw new AppException(ErrorCode.FRIEND_EXISTED);
        }

        if (username.equals(userService.getCurrentUser().getUsername())) {
            throw new AppException(ErrorCode.CANNOT_ADD_YOURSELF);
        }

        Friendship friendship = Friendship.builder()
                .user1(userService.getCurrentUser())
                .user2(user)
                .status(FriendshipStatus.PENDING)
                .build();

        String message = userService.getCurrentUser().getUsername() + " just sent you a friend request.";

        Notification notification = Notification.builder()
                .type(NotificationType.FRIEND_REQUEST)
                .content(message)
                .isRead(false)
                .user(user)
                .build();

        notificationService.createNotification(notification);

        friendshipRepository.save(friendship);
    }

    public void changeStatus(String username, String status) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Friendship friendship = friendshipRepository.findByUser1AndUser2(user, userService.getCurrentUser())
                .orElseThrow(() -> new AppException(ErrorCode.FRIEND_NOT_EXISTED));

        if (!friendship.getUser2().getId().equals(userService.getCurrentUser().getId())) {
            throw new AppException(ErrorCode.ONLY_RECEIVER);
        }

        if (!friendship.getStatus().equals(FriendshipStatus.PENDING)) {
            throw new AppException(ErrorCode.WRONG_REQUEST_STATUS);
        }

        friendship.setStatus(FriendshipStatus.valueOf(status));

        friendshipRepository.save(friendship);
    }
}
