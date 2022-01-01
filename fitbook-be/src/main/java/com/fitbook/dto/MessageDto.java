package com.fitbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private Long id;

    private String content;

    private LocalDateTime createdTime;

    private String sender;

    private String receiver;

    private Long chatId;
}
