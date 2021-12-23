package com.fitbook.service;

import com.fitbook.exception.AccessViolationException;
import com.fitbook.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class Validator {

    private TrainerRepository trainerRepository;

    @Autowired
    public Validator(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public void checkAccessRights(Long trainerId, Principal principal) {
        Long id = trainerRepository.getIdByEmail(principal.getName());
        if (!id.equals(trainerId)) {
            throw new AccessViolationException("Insufficient rights");
        }
    }
}
