package com.fitbook.service.validation;

import com.fitbook.entity.client.Client;
import com.fitbook.entity.program.Program;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import com.fitbook.exception.AccessViolationException;
import com.fitbook.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class Validator {

    private final TrainerService trainerService;

    private final ClientService clientService;

    private final UserService userService;

    private final AuthService authService;

    @Autowired
    public Validator(@Lazy TrainerService trainerService, @Lazy ClientService clientService, UserService userService,
                     AuthService authService) {
        this.trainerService = trainerService;
        this.clientService = clientService;
        this.userService = userService;
        this.authService = authService;
    }

    public void checkTrainerAccessRights(Long trainerId) {
        User user = userService.findById(authService.getUserId());
        Trainer trainer = trainerService.findTrainerByUser(user);
        if (!trainer.getId().equals(trainerId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }

    public void checkTrainerAccessRightsByUser(Long userId) {
        Long id = authService.getUserId();
        if (!id.equals(userId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }

    public void checkTrainerAccessRightsByClient(Long clientId) {
        Long id = authService.getUserId();
        User user = userService.findById(id);
        Trainer trainer = trainerService.findTrainerByUser(user);
        Client client = clientService.findById(clientId);
        if (!trainer.getId().equals(client.getTrainer().getId())) {
            throw new AccessViolationException("Insufficient rights");
        }
    }

    public void checkTrainerAccessRightsByProgram(Long programId) {
        Long id = authService.getUserId();
        User user = userService.findById(id);
        Trainer trainer = trainerService.findTrainerByUser(user);
        if (trainer.getPrograms().stream().map(Program::getId).noneMatch(p -> p.equals(programId))) {
            throw new AccessViolationException("Insufficient rights");
        }
    }

    public void checkClientAccessRights(Long clientId) {
        Long id = authService.getUserId();
        User user = userService.findById(id);
        Client client = clientService.findClientByUser(user);
        if (!client.getId().equals(clientId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }

    public void checkClientAccessRightsByUser(Long userId) {
        Long id = authService.getUserId();
        if (!id.equals(userId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }
}
