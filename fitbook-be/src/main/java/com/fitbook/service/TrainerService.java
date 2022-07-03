package com.fitbook.service;

import com.fitbook.dto.*;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import com.fitbook.enums.NotificationType;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.TrainerRepository;
import com.fitbook.repository.TrainerSpecification;
import com.fitbook.repository.UserRepository;
import com.fitbook.service.validation.Validator;
import com.fitbook.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerService { 

    private final TrainerRepository trainerRepository;

    private final Mapper mapper;

    private final ClientService clientService;

    private final NotificationService notificationService;

    private final UserRepository userRepository;

    private final UserService userService;

    private final Validator validator;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, Mapper mapper, ClientService clientService,
                          NotificationService notificationService, UserRepository userRepository, UserService userService,
                          Validator validator) {
        this.trainerRepository = trainerRepository;
        this.mapper = mapper;
        this.clientService = clientService;
        this.notificationService = notificationService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.validator = validator;
    }

    public List<ProgramDto> findProgramsByUserId(Long userId) {
        try {
            User user = userService.findById(userId);
            Trainer trainer = trainerRepository.findByUser(user);
            return trainer.getPrograms().stream().map(mapper::map).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResourceNotFoundException(String.format("Failed to find trainer with user id %d", userId));
        }
    }

    public Trainer findTrainerByUser(User user) {
        try {
            return trainerRepository.findByUser(user);
        } catch (Exception e) {
            throw new ResourceNotFoundException(String.format("Failed to find trainer with user id %d", user.getId()));
        }
    }

    public TrainerDto findTrainer(Long id) {
        try {
            Optional<Trainer> trainerOpt = trainerRepository.findById(id);
            if (trainerOpt.isEmpty()) {
                return null;
            }
            return mapper.map(trainerOpt.get());
        } catch (Exception e) {
            throw new ResourceNotFoundException(String.format("Failed to find trainer with id %d", id));
        }
    }

    public TrainerDto findTrainerByUserId(Long id) {
        try {
            Optional<User> userOpt = userRepository.findById(id);
            if (userOpt.isEmpty()) {
                return null;
            }
            return mapper.map(trainerRepository.findByUser(userOpt.get()));
        } catch (Exception e) {
            throw new ResourceNotFoundException(String.format("Failed to find user with id %d", id));
        }
    }

    public List<TrainerDto> findTrainers(SearchDto searchDto, int page, int size) {
        if (searchDto == null) {
            return trainerRepository.findAll(PageRequest.of(page, size)).stream()
                    .map(mapper::map).collect(Collectors.toList());
        }
        return trainerRepository.findAll(TrainerSpecification.findTrainers(searchDto), PageRequest.of(page, size)).stream()
                .map(mapper::map).collect(Collectors.toList());
    }

    public List<ClientDto> findClientsByTrainerUserId(Long userId) {
        User user = userService.findById(userId);
        Trainer trainer = trainerRepository.findByUser(user);
        if (trainer == null) {
            throw new ResourceNotFoundException(String.format("Trainer with user id %d not found.", userId));
        }

        return trainer.getClients().stream().map(mapper::map).collect(Collectors.toList());
    }

    public Boolean sendRequest(Long trainerId, Long userId) {
        User user = userService.findById(userId);
        Client client = clientService.findClientByUser(user);
        Trainer trainer = findById(trainerId);

        try {
            notificationService.sendNotification(trainer.getUser(), NotificationType.REQUEST_SENT, trainer, client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Trainer findById(Long id) {
        Optional<Trainer> trainerOpt = trainerRepository.findById(id);
        if (trainerOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Trainer with id %d not found.", id));
        }

        return trainerOpt.get();
    }

    public Trainer create(User user, RegistrationDto registrationDto) {
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setFirstName(registrationDto.getFirstName());
        trainer.setLastName(registrationDto.getLastName());
        trainer.setCity(registrationDto.getCity());
        trainer.setGender(registrationDto.getGender());
        return trainerRepository.save(trainer);
    }

    public TrainerDto update(Long id, TrainerDto trainerDto) {
        Optional<Trainer> trainerOpt = trainerRepository.findById(id);
        if (trainerOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Trainer with id %d not found.", id));
        }

        mapper.map(trainerOpt.get(), trainerDto);

        return mapper.map(trainerRepository.save(trainerOpt.get()));
    }

    public List<ChatDto> getChats(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = userService.findByEmail(email);
        Trainer trainer = trainerRepository.findByUser(user);
        if (trainer == null) {
            throw new ResourceNotFoundException(String.format("Trainer with user id %d not found.", user.getId()));
        }

        return trainer.getChats().stream().map(mapper::map).collect(Collectors.toList());
    }

    public boolean handleRequest(Long userId, Long clientId) {
        try {
            User user = userService.findById(userId);
            Trainer trainer = findTrainerByUser(user);
            Client client = clientService.findById(clientId);
            client.setTrainer(trainer);
            clientService.update(client);
            notificationService.sendNotification(client.getUser(), NotificationType.REQUEST_ACCEPTED, trainer, client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeClientFromList(Long clientId) {
        try {
            Client client = clientService.findById(clientId);
            client.setTrainer(null);
            clientService.update(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeProgramFromList(Long programId, Authentication authentication) {
        try {
            String email = (String) authentication.getPrincipal();
            User user = userService.findByEmail(email);
            Trainer trainer = findTrainerByUser(user);
            trainer.getPrograms().removeIf(p -> p.getId().equals(programId));
            trainerRepository.save(trainer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Trainer save(Trainer trainer) {
        return trainerRepository.save(trainer);
    }
}
