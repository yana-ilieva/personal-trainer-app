package com.fitbook.repository;

import com.fitbook.entity.client.Client;
import com.fitbook.entity.notification.Notification;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.enums.NotificationType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByNotificationTypeAndClientAndResolvedFalse(NotificationType type, Client client, Pageable pageable);

    List<Notification> findAllByNotificationTypeAndTrainerAndResolvedFalse(NotificationType type, Trainer trainer, Pageable pageable);

    List<Notification> findByClientAndTrainerAndNotificationTypeResolvedFalse(Client client, Trainer trainer, NotificationType type);
}
