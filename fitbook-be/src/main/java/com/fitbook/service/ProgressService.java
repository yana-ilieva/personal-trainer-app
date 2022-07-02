package com.fitbook.service;

import com.fitbook.dto.ProgressDto;
import com.fitbook.entity.client.Progress;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.ProgressRepository;
import com.fitbook.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProgressService {

    private final ProgressRepository progressRepository;

    private final Mapper mapper;

    private final ClientService clientService;

    @Autowired
    public ProgressService(ProgressRepository progressRepository, Mapper mapper, ClientService clientService) {
        this.progressRepository = progressRepository;
        this.mapper = mapper;
        this.clientService = clientService;
    }

    public ProgressDto create(ProgressDto progressDto) {
        Progress progress = mapper.map(progressDto);
        progress.setCreatedTimestamp(LocalDateTime.now());
        progress.setClient(clientService.findById(progressDto.getClientId()));
        return mapper.map(progressRepository.save(progress));
    }

    public ProgressDto update(Long id, ProgressDto progressDto) {
        Optional<Progress> progressOpt = progressRepository.findById(id);
        if (progressOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Progress with id %d not found", id));
        }

        mapper.map(progressDto, progressOpt.get());

        return mapper.map(progressRepository.save(progressOpt.get()));
    }
}
