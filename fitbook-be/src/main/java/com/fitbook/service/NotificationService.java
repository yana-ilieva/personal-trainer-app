package com.fitbook.service;

import com.fitbook.dto.NotificationDto;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.notification.Notification;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import com.fitbook.enums.NotificationType;
import com.fitbook.repository.NotificationRepository;
import com.fitbook.util.DateUtil;
import com.fitbook.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final Mapper mapper;

    private final ClientService clientService;

    private final TrainerService trainerService;

    private final UserService userService;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, SimpMessagingTemplate simpMessagingTemplate,
                               Mapper mapper, ClientService clientService, @Lazy TrainerService trainerService, @Lazy UserService userService) {
        this.notificationRepository = notificationRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.mapper = mapper;
        this.clientService = clientService;
        this.trainerService = trainerService;
        this.userService = userService;
    }

    public List<NotificationDto> findAll(Long userId, int page, int size) {
        User user = userService.findById(userId);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdTimestamp"));
        if (user.getRole().getName().equals("ROLE_CLIENT")) {
            Client client = clientService.findClientByUser(user);
            return notificationRepository.findAllByNotificationTypeAndClientAndResolvedFalse(NotificationType.REQUEST_ACCEPTED, client, pageRequest).stream()
                    .map(mapper::map).collect(Collectors.toList());
        }
        Trainer trainer = trainerService.findTrainerByUser(user);
        return notificationRepository.findAllByNotificationTypeAndTrainerAndResolvedFalse(NotificationType.REQUEST_SENT, trainer, pageRequest).stream()
                .map(mapper::map).collect(Collectors.toList());
    }

    private NotificationDto create(NotificationType notificationType, Trainer trainer, Client client) {
        Notification notification = new Notification();
        notification.setNotificationType(notificationType);
        notification.setCreatedTimestamp(DateUtil.now());
        notification.setClient(client);
        notification.setTrainer(trainer);
        notification.setResolved(false);

        return mapper.map(notificationRepository.save(notification));
    }

    private void resolveNotification(Client client, Trainer trainer) {
        List<Notification> notifications = notificationRepository.findByClientAndTrainerAndNotificationTypeResolvedFalse(client, trainer, NotificationType.REQUEST_SENT);
        for (Notification notification : notifications) {
            notification.setResolved(true);
        }
        notificationRepository.saveAll(notifications);
    }

    public void sendNotification(User user, NotificationType notificationType, Trainer trainer, Client client) {
        NotificationDto notificationDto = create(notificationType, trainer, client);
        if (notificationType == NotificationType.REQUEST_ACCEPTED) {
            resolveNotification(client, trainer);
        }
        simpMessagingTemplate.convertAndSendToUser(user.getEmail(), "/queue/notifications", notificationDto);
    }
}
