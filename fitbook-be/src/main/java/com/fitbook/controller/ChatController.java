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
@CrossOrigin(origins = "http://localhost:8080")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat/{receiver_id}")
    public void sendSpecific(@PathVariable("receiver_id") Long receiverId, @Payload MessageDto message, Authentication authentication) {
        chatService.send(receiverId, message, authentication);
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
