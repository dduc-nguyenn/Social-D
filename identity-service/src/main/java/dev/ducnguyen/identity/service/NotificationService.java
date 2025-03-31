package dev.ducnguyen.identity.service;

import dev.ducnguyen.identity.entity.Notification;
import dev.ducnguyen.identity.mapper.NotificationMapper;
import dev.ducnguyen.identity.repository.NotificationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationService {

    NotificationRepository notificationRepository;
    NotificationMapper notificationMapper;

    public void createNotification(Notification notification) {

        notificationRepository.save(notification);
    }
}
