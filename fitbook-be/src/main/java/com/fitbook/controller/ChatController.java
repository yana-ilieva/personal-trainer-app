package com.fitbook.controller;

import com.fitbook.dto.ChatDto;
import com.fitbook.dto.MessageDto;
import com.fitbook.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat/{to}")
    public void sendSpecific(@PathVariable("to") String to, @Payload MessageDto message, Authentication authentication) {
        chatService.send(to, message, authentication);
    }

    @PostMapping("/api/chat")
    public ChatDto initializeChat(@RequestBody ChatDto chatDto) {
        return chatService.initializeChat(chatDto);
    }

    @GetMapping("/api/chat/{id}/messages/{page}")
    public List<MessageDto> getMessages(@PathVariable("id") Long chatId, @PathVariable("page") int page) {
        return chatService.getMessages(chatId, page);
    }
}
