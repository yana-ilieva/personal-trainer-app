package com.fitbook.service;

import com.fitbook.dto.ClientDto;
import com.fitbook.dto.ProgressDto;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.client.Progress;
import com.fitbook.entity.program.Program;
import com.fitbook.entity.user.User;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.ClientRepository;
import com.fitbook.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final Mapper mapper;

    private final ProgramService programService;

    private final ProgressService progressService;

    @Autowired
    public ClientService(ClientRepository clientRepository, Mapper mapper, ProgramService programService,
                         ProgressService progressService) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
        this.programService = programService;
        this.progressService = progressService;
    }

    public Long create(User user) {
        Client client = new Client();
        client.setUser(user);
        return clientRepository.save(client).getId();
    }

    public ClientDto update(Long id, ClientDto clientDto) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Client with id %d not found", id));
        }

        mapper.map(clientOpt.get(), clientDto);

        return mapper.map(clientRepository.save(clientOpt.get()));
    }

    public Client findById(Long id) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Client with id %d not found", id));
        }
        return clientOpt.get();
    }

    public ClientDto assignProgramToClient(Long clientId, Long programId) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (clientOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Client with id %d not found", clientId));
        }
        Program program = programService.findById(programId);
        clientOpt.get().setProgram(program);

        return mapper.map(clientRepository.save(clientOpt.get()));
    }

    public List<ProgressDto> getProgress(Long id, int page, int size) {
        return clientRepository.getProgress(id, PageRequest.of(page, size)).stream()
                .map(mapper::map).collect(Collectors.toList());
    }
}
