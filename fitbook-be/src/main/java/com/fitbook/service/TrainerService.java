package com.fitbook.service;

import com.fitbook.dto.ClientDto;
import com.fitbook.dto.SearchDto;
import com.fitbook.dto.TrainerDto;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.trainer.Request;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.RequestRepository;
import com.fitbook.repository.TrainerRepository;
import com.fitbook.repository.TrainerSpecification;
import com.fitbook.util.DateUtil;
import com.fitbook.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerService { 

    private final TrainerRepository trainerRepository;

    private final Mapper mapper;

    private final RequestRepository requestRepository;

    private final ClientService clientService;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, Mapper mapper, RequestRepository requestRepository, ClientService clientService) {
        this.trainerRepository = trainerRepository;
        this.mapper = mapper;
        this.requestRepository = requestRepository;
        this.clientService = clientService;
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

    public List<TrainerDto> findTrainers(SearchDto searchDto, int page, int size) {
        if (searchDto == null) {
            return trainerRepository.findAll(PageRequest.of(page, size)).stream()
                    .map(mapper::map).collect(Collectors.toList());
        }
        return trainerRepository.findAll(TrainerSpecification.findTrainers(searchDto), PageRequest.of(page, size)).stream()
                .map(mapper::map).collect(Collectors.toList());
    }

    public List<ClientDto> findClientsByTrainer(Long id) {
        Optional<Trainer> trainerOpt = trainerRepository.findById(id);
        if (trainerOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Trainer with id %d not found.", id));
        }

        return trainerOpt.get().getClients().stream().map(mapper::map).collect(Collectors.toList());
    }

    public Boolean sendRequest(Long trainerId, Long clientId) {
        Client client = clientService.findById(clientId);
        Trainer trainer = findById(trainerId);

        Request request = Request.builder().client(client).trainer(trainer).submitTime(DateUtil.now()).build();

        try {
            requestRepository.save(request);
            // todo: make notification
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Trainer findById(Long id) {
        Optional<Trainer> trainerOpt = trainerRepository.findById(id);
        if (trainerOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Trainer with id %d not found.", id));
        }

        return trainerOpt.get();
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
