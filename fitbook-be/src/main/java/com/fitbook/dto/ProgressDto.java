package com.fitbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressDto {

    private Long id;

    private Long clientId;

    private LocalDateTime createdTimestamp;

    private Double weight;

    private Double chestMeasurement;

    private Double waistMeasurement;

    private Double hipMeasurement;

    private Double thighMeasurement;

    private Double upperArmMeasurement;
}
