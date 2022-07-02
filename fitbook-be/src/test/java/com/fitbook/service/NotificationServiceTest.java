package com.fitbook.service;

import com.fitbook.dto.NotificationDto;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.notification.Notification;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.enums.NotificationType;
import com.fitbook.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Sql(scripts = {"/notifications-clear.sql", "/notifications.sql"})
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private NotificationRepository notificationRepository;

    @MockBean
    private SimpMessagingTemplate simpMessagingTemplate;

    @Test
    public void findAll_trainer() {
        List<NotificationDto> notifications = notificationService.findAll(2L, 0, 10);
        assertEquals(2, notifications.size());
        assertEquals(NotificationType.REQUEST_SENT, notifications.get(0).getNotificationType());
        assertEquals(LocalDateTime.of(2022, 6, 4, 10, 0), notifications.get(0).getCreatedTimeStamp());
        assertEquals(NotificationType.REQUEST_SENT, notifications.get(1).getNotificationType());
        assertEquals(LocalDateTime.of(2022, 6, 3, 10, 0), notifications.get(1).getCreatedTimeStamp());
    }

    @Test
    public void findAll_client() {
        List<NotificationDto> notifications = notificationService.findAll(1L, 0, 10);
        assertEquals(1, notifications.size());
        assertEquals(NotificationType.REQUEST_ACCEPTED, notifications.get(0).getNotificationType());
        assertEquals(LocalDateTime.of(2022, 6, 2, 10, 0), notifications.get(0).getCreatedTimeStamp());
    }

    @Test
    @Transactional
    public void sendNotification() {
        Mockito.doNothing().when(simpMessagingTemplate).convertAndSendToUser(any(), any(), any());

        Client client = clientService.findById(5L);
        Trainer trainer = trainerService.findById(1L);

        notificationService.sendNotification(trainer.getUser(), NotificationType.REQUEST_SENT, trainer, client);

        List<Notification> notification = notificationRepository.findByClientAndTrainerAndNotificationTypeAndResolvedFalse(client, trainer, NotificationType.REQUEST_SENT);

        assertNotNull(notification);
    }
}