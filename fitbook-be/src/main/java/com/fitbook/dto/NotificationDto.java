package com.fitbook.dto;

import com.fitbook.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    private Long id;

    private NotificationType notificationType;

    private LocalDateTime createdTimeStamp;

    private Long trainerId;

    private Long clientId;

    private String trainerName;

    private String clientName;
}
