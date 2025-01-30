package dev.michaelcao512.revshop_springboot.DTO;

import dev.michaelcao512.revshop_springboot.Entities.Notification;

import java.io.Serializable;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.Notification}
 */
public record NotificationDto(Long userUserId, String message,
                              Notification.NotificationType type) implements Serializable {
}