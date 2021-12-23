package com.fitbook.controller;

import com.fitbook.entity.chat.Message;
import com.fitbook.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat.message")
    public void sendSpecific(@Payload Message message, Principal user, @Header("simpSessionId") String sessionId) {
        message.setTimestamp(DateUtil.now());
        simpMessagingTemplate.convertAndSend("/user/" + user.getName() + "/exchange/amq.direct/chat.message", message);
    }
}
