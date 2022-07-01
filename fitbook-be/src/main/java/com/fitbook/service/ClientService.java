package com.fitbook.service;

import com.fitbook.dto.*;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.program.NutritionPlan;
import com.fitbook.entity.program.Program;
import com.fitbook.entity.user.User;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.ClientRepository;
import com.fitbook.repository.ProgressRepository;
import com.fitbook.repository.UserRepository;
import com.fitbook.util.Mapper;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final Mapper mapper;

    private final ProgramService programService;

    private final NutritionPlanService nutritionPlanService;

    private final UserRepository userRepository;

    private final UserService userService;

    private final ProgressRepository progressRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, Mapper mapper, @Lazy ProgramService programService,
                         NutritionPlanService nutritionPlanService, UserRepository userRepository, UserService userService,
                         ProgressRepository progressRepository) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
        this.programService = programService;
        this.nutritionPlanService = nutritionPlanService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.progressRepository = progressRepository;
    }

    public ClientFullDto getFullDto(Long id) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Client with id %d not found", id));
        }

        return mapper.mapFull(clientOpt.get());
    }

    public ProgramDto getProgram(Long userId) {
        try {
            User user = userService.findById(userId);
            return mapper.map(clientRepository.findByUser(user).getProgram());
        } catch (Exception e) {
            throw new ResourceNotFoundException(String.format("Client with user id %d not found", userId));
        }
    }

    public NutritionPlanDto getNutritionPlan(Long userId) {
        try {
            User user = userService.findById(userId);
            return mapper.map(clientRepository.findByUser(user).getNutritionPlan());
        } catch (Exception e) {
            throw new ResourceNotFoundException(String.format("Client with user id %d not found", userId));
        }
    }

    public Client findClientByUser(User user) {
        try {
            return clientRepository.findByUser(user);
        } catch (Exception e) {
            throw new ResourceNotFoundException(String.format("Client with user id %d not found", user.getId()));
        }
    }

    public ClientDto findClientByUserId(Long id) {
        try {
            Optional<User> userOpt = userRepository.findById(id);
            if (userOpt.isEmpty()) {
                return null;
            }
            return mapper.map(clientRepository.findByUser(userOpt.get()));
        } catch (Exception e) {
            throw new ResourceNotFoundException(String.format("Client with user id %d not found", id));
        }
    }

    public Client create(User user, RegistrationDto registrationDto) {
        Client client = new Client();
        client.setUser(user);
        client.setFirstName(registrationDto.getFirstName());
        client.setLastName(registrationDto.getLastName());
        client.setGender(registrationDto.getGender());
        return clientRepository.save(client);
    }

    public Client update(Client client) {
        return clientRepository.save(client);
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

    @Transactional
    public ClientDto assignProgramToClient(Long clientId, Long programId) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (clientOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Client with id %d not found", clientId));
        }
        Program program = programService.findById(programId);
        Program programCopy = programCopy(program);
        programCopy = programService.create(programCopy);
        clientOpt.get().setProgram(programCopy);

        return mapper.map(clientRepository.save(clientOpt.get()));
    }

    private Program programCopy(Program program) {
        Program newProgram = SerializationUtils.clone(program);
        newProgram.setId(null);
        newProgram.getProgramParts().forEach(part -> part.setId(null));
        newProgram.getProgramParts().forEach(part -> part.getExerciseUnits().forEach(unit -> unit.setId(null)));
        return newProgram;
    }

    public ClientDto assignNutritionPlanToClient(Long clientId, Long nutritionPlanId) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (clientOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Client with id %d not found", clientId));
        }

        NutritionPlan nutritionPlan = nutritionPlanService.findById(nutritionPlanId);
        NutritionPlan nutritionPlanCopy = nutritionPlan;
        nutritionPlanCopy = nutritionPlanService.create(nutritionPlanCopy);

        clientOpt.get().setNutritionPlan(nutritionPlanCopy);

        return mapper.map(clientRepository.save(clientOpt.get()));
    }


    public List<ProgressDto> getProgress(Long id, int page, int size) {
        Client client = findById(id);
        return progressRepository.findByClient(client, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdTimestamp")))
                .stream().map(mapper::map).collect(Collectors.toList());
    }

    public List<ChatDto> getChats(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = userService.findByEmail(email);
        Client client = clientRepository.findByUser(user);
        if (client == null) {
            throw new ResourceNotFoundException(String.format("Client with user id %d not found", user.getId()));
        }

        return client.getChats().stream().map(mapper::map).collect(Collectors.toList());
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
