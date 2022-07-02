package com.fitbook.service.validation;

import com.fitbook.entity.user.User;
import com.fitbook.exception.AccessViolationException;
import com.fitbook.repository.ClientRepository;
import com.fitbook.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class Validator {

    private final TrainerRepository trainerRepository;

    private final ClientRepository clientRepository;

    @Autowired
    public Validator(TrainerRepository trainerRepository, ClientRepository clientRepository) {
        this.trainerRepository = trainerRepository;
        this.clientRepository = clientRepository;
    }

    public void checkTrainerAccessRights(Long trainerId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Long id = trainerRepository.getIdByEmail(user.getEmail());
        if (!id.equals(trainerId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }

    public void checkClientAccessRights(Long clientId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Long id = clientRepository.getIdByEmail(user.getEmail());
        if (!id.equals(clientId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }
}
