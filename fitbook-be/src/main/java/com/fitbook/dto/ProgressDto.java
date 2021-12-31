package com.fitbook.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProgressDto {

    private Long id;

    private LocalDateTime createdTimestamp;
}
