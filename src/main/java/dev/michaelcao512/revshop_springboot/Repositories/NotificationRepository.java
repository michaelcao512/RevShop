package dev.michaelcao512.revshop_springboot.Repositories;

import dev.michaelcao512.revshop_springboot.Entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}