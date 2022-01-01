package com.fitbook.service;

import com.fitbook.dto.NotificationDto;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.notification.Notification;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.enums.NotificationType;
import com.fitbook.repository.NotificationRepository;
import com.fitbook.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, SimpMessagingTemplate simpMessagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void create(NotificationType notificationType, Trainer trainer, Client client) {

        Notification notification = new Notification();
        notification.setNotificationType(notificationType);
        notification.setCreatedTimestamp(DateUtil.now());
        notification.setClient(client);
        notification.setTrainer(trainer);

        notificationRepository.save(notification);
    }

    public void sendNotification(String username, NotificationDto notificationDto) {
        simpMessagingTemplate.convertAndSendToUser(username, "/queue/notifications", notificationDto);
    }
}
