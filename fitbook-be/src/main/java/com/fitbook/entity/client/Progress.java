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

    private Double weight;

    @Column(name = "chest_measurement")
    private Double chestMeasurement;

    @Column(name = "waist_measurement")
    private Double waistMeasurement;

    @Column(name = "hip_measurement")
    private Double hipMeasurement;

    @Column(name = "thigh_measurement")
    private Double thighMeasurement;

    @Column(name = "upper_arm_measurement")
    private Double upperArmMeasurement;
}
