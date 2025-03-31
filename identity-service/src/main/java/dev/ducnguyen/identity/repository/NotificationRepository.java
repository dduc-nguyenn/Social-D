package dev.ducnguyen.identity.repository;

import dev.ducnguyen.identity.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, String> {

}