package com.fitbook.service;

import com.fitbook.dto.ChatDto;
import com.fitbook.dto.ClientShortDto;
import com.fitbook.dto.MessageDto;
import com.fitbook.dto.TrainerShortDto;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Sql(scripts = {"/trainers-clear.sql", "/trainers.sql"})
public class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @MockBean
    private SimpMessagingTemplate simpMessagingTemplate;

    @MockBean
    private AuthService authService;

    @Test
    @Transactional
    public void send() {
        Mockito.when(authService.getUserId()).thenReturn(1L);
        Mockito.doNothing().when(simpMessagingTemplate).convertAndSendToUser(any(), any(), any());
        ChatDto chatDto = new ChatDto();
        chatDto.setTrainer(new TrainerShortDto(10L, "", null));
        chatDto.setClient(new ClientShortDto(10L, "", null));
        ChatDto chatDto1 = chatService.initializeChat(chatDto);

        MessageDto messageDto = new MessageDto();
        messageDto.setChatId(chatDto1.getId());
        messageDto.setSender("Client");
        messageDto.setReceiver("Trainer");
        messageDto.setContent("Hello");
        chatService.send(messageDto);

        Mockito.verify(simpMessagingTemplate, Mockito.times(2)).convertAndSendToUser(any(), any(), any());
    }

    @Test
    public void initializeChat() {
        ChatDto chatDto = new ChatDto();
        chatDto.setTrainer(new TrainerShortDto(10L, "", null));
        chatDto.setClient(new ClientShortDto(10L, "", null));
        ChatDto chatDto1 = chatService.initializeChat(chatDto);
        assertNotNull(chatDto1);
        assertEquals(10L, chatDto1.getClient().getId());
        assertEquals(10L, chatDto1.getTrainer().getId());
    }

    @Test
    public void getMessages() {
        Mockito.when(authService.getUserId()).thenReturn(1L);
        Mockito.doNothing().when(simpMessagingTemplate).convertAndSendToUser(any(), any(), any());
        ChatDto chatDto = new ChatDto();
        chatDto.setTrainer(new TrainerShortDto(10L, "", null));
        chatDto.setClient(new ClientShortDto(10L, "", null));
        ChatDto chatDto1 = chatService.initializeChat(chatDto);

        MessageDto messageDto = new MessageDto();
        messageDto.setChatId(chatDto1.getId());
        messageDto.setSender("Client");
        messageDto.setReceiver("Trainer");
        messageDto.setContent("Hello");
        chatService.send(messageDto);

        List<MessageDto> messages = chatService.getMessages(chatDto1.getId(), 0);
        assertEquals(1, messages.size());
        assertEquals("Hello", messages.get(0).getContent());
    }
}