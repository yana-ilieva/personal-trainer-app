package com.fitbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private Long id;

    private TrainerShortDto trainer;

    private ClientShortDto client;
}
