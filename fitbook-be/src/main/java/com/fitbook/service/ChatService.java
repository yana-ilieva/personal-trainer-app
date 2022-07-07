package com.fitbook.service;

import com.fitbook.dto.ChatDto;
import com.fitbook.dto.MessageDto;
import com.fitbook.entity.chat.Chat;
import com.fitbook.entity.chat.Message;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.ChatRepository;
import com.fitbook.repository.MessageRepository;
import com.fitbook.util.DateUtil;
import com.fitbook.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final ChatRepository chatRepository;

    private final Mapper mapper;

    private final ClientService clientService;

    private final TrainerService trainerService;

    private final MessageRepository messageRepository;
    
    private final UserService userService;

    private final AuthService authService;

    @Autowired
    public ChatService(SimpMessagingTemplate simpMessagingTemplate, ChatRepository chatRepository, Mapper mapper,
                       ClientService clientService, TrainerService trainerService, MessageRepository messageRepository,
                       UserService userService, AuthService authService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatRepository = chatRepository;
        this.mapper = mapper;
        this.clientService = clientService;
        this.trainerService = trainerService;
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.authService = authService;
    }

    @Transactional
    public void send(MessageDto messageDto) {
        Message message = mapper.map(messageDto);
        message.setCreatedTime(DateUtil.now());

        Optional<Chat> chatOpt = chatRepository.findById(messageDto.getChatId());
        if (chatOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Chat with id %d not found", messageDto.getChatId()));
        }

        message.setChat(chatOpt.get());
        if (chatOpt.get().getMessages() == null) {
            chatOpt.get().setMessages(new ArrayList<>());
        }
        chatOpt.get().getMessages().add(message);
        Chat chat = chatRepository.save(chatOpt.get());

        MessageDto msg = mapper.map(message);

        Long senderId = authService.getUserId();
        User sender = userService.findById(senderId);
        User receiver;

        if (sender.getRole().getName().equals("ROLE_TRAINER")) {
            receiver = chat.getClient().getUser();
        } else {
            receiver = chat.getTrainer().getUser();
        }

        simpMessagingTemplate.convertAndSendToUser(senderId.toString(), "/queue/messages", msg);
        simpMessagingTemplate.convertAndSendToUser(receiver.getId().toString(), "/queue/messages", msg);
    }

    public ChatDto initializeChat(ChatDto chatDto) {
        Chat chat = mapper.map(chatDto);

        Client client = clientService.findById(chatDto.getClient().getId());
        Trainer trainer = trainerService.findById(chatDto.getTrainer().getId());

        Optional<Chat> chatOpt = chatRepository.findByClientAndTrainer(client, trainer);

        if (chatOpt.isPresent()) {
            return mapper.map(chatOpt.get());
        }

        chat.setClient(client);
        chat.setTrainer(trainer);

        return mapper.map(chatRepository.save(chat));
    }

    public List<MessageDto> getMessages(Long chatId, int page){
        Optional<Chat> chatOpt = chatRepository.findById(chatId);
        if (chatOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Chat with id %d not found", chatId));
        }

        List<Message> messages = messageRepository.getMessages(chatOpt.get(), PageRequest.of(page, 10, Sort.by("createdTime")));
        return messages.stream().map(mapper::map).collect(Collectors.toList());
    }
}
