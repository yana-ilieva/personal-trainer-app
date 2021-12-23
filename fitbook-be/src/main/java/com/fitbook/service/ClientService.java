package com.fitbook.service;

import com.fitbook.dto.ClientDto;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.user.User;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.ClientRepository;
import com.fitbook.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final Mapper mapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, Mapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
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
}
