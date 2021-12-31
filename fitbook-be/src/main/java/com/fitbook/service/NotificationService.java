package com.fitbook.service;

import com.fitbook.entity.client.Client;
import com.fitbook.entity.notification.Notification;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.enums.NotificationType;
import com.fitbook.repository.NotificationRepository;
import com.fitbook.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void create(NotificationType notificationType, Trainer trainer, Client client) {
        Notification notification = Notification.builder()
                .notificationType(notificationType)
                .createdTimestamp(DateUtil.now())
                .trainer(trainer)
                .client(client)
                .build();
        notificationRepository.save(notification);


    }
}
