package com.fitbook.entity.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "progress")
@Entity
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "calories_burned")
    private Long caloriesBurned;

    @Column(name = "bmi")
    private Double bmi;
}
