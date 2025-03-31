package dev.ducnguyen.identity.mapper;

import dev.ducnguyen.identity.dto.response.NotificationResponse;
import dev.ducnguyen.identity.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationResponse toNotificationResponse(Notification notification);
}
