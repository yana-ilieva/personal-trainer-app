package com.fitbook.entity.notification;

import com.fitbook.entity.client.Client;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notifications")
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private NotificationType notificationType;

    private LocalDateTime createdTimestamp;

    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
}
