package com.fitbook.service.validation;

import com.fitbook.exception.AccessViolationException;
import com.fitbook.repository.ClientRepository;
import com.fitbook.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class Validator {

    private final TrainerRepository trainerRepository;

    private final ClientRepository clientRepository;

    @Autowired
    public Validator(TrainerRepository trainerRepository, ClientRepository clientRepository) {
        this.trainerRepository = trainerRepository;
        this.clientRepository = clientRepository;
    }

    public void checkTrainerAccessRights(Long trainerId, Principal principal) {
        Long id = trainerRepository.getIdByEmail(principal.getName());
        if (!id.equals(trainerId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }

    public void checkClientAccessRights(Long clientId, Principal principal) {
        Long id = clientRepository.getIdByEmail(principal.getName());
        if (!id.equals(clientId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }
}
