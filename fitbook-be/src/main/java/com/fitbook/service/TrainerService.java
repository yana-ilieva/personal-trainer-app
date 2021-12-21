package com.fitbook.service;

import com.fitbook.dto.SearchDto;
import com.fitbook.dto.TrainerDto;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.TrainerRepository;
import com.fitbook.repository.TrainerSpecification;
import com.fitbook.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerService { 
    
    @Autowired
    private TrainerRepository trainerRepository;
    
    @Autowired
    private Mapper mapper;
    
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

    public List<TrainerDto> findTrainers(SearchDto searchDto, int page, int size) {
        if (searchDto == null) {
            return trainerRepository.findAll(PageRequest.of(page, size)).stream()
                    .map(trainer -> mapper.map(trainer)).collect(Collectors.toList());
        }
        return trainerRepository.findAll(TrainerSpecification.findTrainers(searchDto), PageRequest.of(page, size)).stream()
                .map(trainer -> mapper.map(trainer)).collect(Collectors.toList());
    }

    public Long create(User user) {
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        return trainerRepository.save(trainer).getId();
    }

    public TrainerDto update(Long id, TrainerDto trainerDto) {
        Optional<Trainer> trainerOpt = trainerRepository.findById(id);
        if (trainerOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Trainer with id %d not found.", id));
        }

        mapper.map(trainerOpt.get(), trainerDto);

        return mapper.map(trainerRepository.save(trainerOpt.get()));
    }
}
