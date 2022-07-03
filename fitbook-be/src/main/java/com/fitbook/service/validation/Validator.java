package com.fitbook.service.validation;

import com.fitbook.entity.client.Client;
import com.fitbook.entity.program.Program;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import com.fitbook.exception.AccessViolationException;
import com.fitbook.service.ClientService;
import com.fitbook.service.ProgramService;
import com.fitbook.service.TrainerService;
import com.fitbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class Validator {

    private final TrainerService trainerService;

    private final ClientService clientService;

    private final UserService userService;

    private final ProgramService programService;

    @Autowired
    public Validator(TrainerService trainerService, ClientService clientService, UserService userService, ProgramService programService) {
        this.trainerService = trainerService;
        this.clientService = clientService;
        this.userService = userService;
        this.programService = programService;
    }

    public void checkTrainerAccessRights(Long trainerId, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = userService.findByEmail(email);
        Trainer trainer = trainerService.findTrainerByUser(user);
        if (!trainer.getId().equals(trainerId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }

    public void checkTrainerAccessRightsByUser(Long userId, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = userService.findByEmail(email);
        if (!user.getId().equals(userId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }

    public void checkTrainerAccessRightsByClient(Long clientId, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = userService.findByEmail(email);
        Trainer trainer = trainerService.findTrainerByUser(user);
        Client client = clientService.findById(clientId);
        if (!trainer.getId().equals(client.getTrainer().getId())) {
            throw new AccessViolationException("Insufficient rights");
        }
    }

    public void checkTrainerAccessRightsByProgram(Long programId, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = userService.findByEmail(email);
        Trainer trainer = trainerService.findTrainerByUser(user);
        if (trainer.getPrograms().stream().map(Program::getId).noneMatch(id -> id.equals(programId))) {
            throw new AccessViolationException("Insufficient rights");
        }
    }

    public void checkClientAccessRights(Long clientId, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = userService.findByEmail(email);
        Client client = clientService.findClientByUser(user);
        if (!client.getId().equals(clientId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }

    public void checkClientAccessRightsByUser(Long userId, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = userService.findByEmail(email);
        if (!user.getId().equals(userId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }
}
