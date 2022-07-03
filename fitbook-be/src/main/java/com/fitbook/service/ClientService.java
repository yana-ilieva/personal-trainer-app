package com.fitbook.service;

import com.fitbook.dto.*;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.program.ExerciseUnit;
import com.fitbook.entity.program.Program;
import com.fitbook.entity.program.ProgramPart;
import com.fitbook.entity.user.User;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.ClientRepository;
import com.fitbook.repository.ProgressRepository;
import com.fitbook.repository.UserRepository;
import com.fitbook.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final Mapper mapper;

    private final ProgramService programService;

    private final UserRepository userRepository;

    private final UserService userService;

    private final ProgressRepository progressRepository;

    private final AuthService authService;

    @Autowired
    public ClientService(ClientRepository clientRepository, Mapper mapper, @Lazy ProgramService programService,
                         UserRepository userRepository, UserService userService, ProgressRepository progressRepository,
                         AuthService authService) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
        this.programService = programService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.progressRepository = progressRepository;
        this.authService = authService;
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
        clientOpt.get().setProgram(programCopy);
        Client save = clientRepository.save(clientOpt.get());
        return mapper.map(save);
    }

    private Program programCopy(Program program) {
        Program newProgram = new Program();
        newProgram.setName(program.getName());
        newProgram.setDescription(program.getDescription());
        newProgram.setProgramParts(copyProgramParts(program.getProgramParts(), newProgram));
        return newProgram;
    }

    private List<ProgramPart> copyProgramParts(List<ProgramPart> programParts, Program parent) {
        List<ProgramPart> parts = new ArrayList<>();
        if (programParts != null) {
            for (ProgramPart programPart : programParts) {
                ProgramPart newProgramPart = new ProgramPart();
                newProgramPart.setWeekDay(programPart.getWeekDay());
                newProgramPart.setProgram(parent);
                newProgramPart.setExerciseUnits(copyExerciseUnits(programPart.getExerciseUnits(), newProgramPart));
                parts.add(newProgramPart);
            }
        }
        return parts;
    }

    private List<ExerciseUnit> copyExerciseUnits(List<ExerciseUnit> exerciseUnits, ProgramPart parent) {
        List<ExerciseUnit> units = new ArrayList<>();
        if (exerciseUnits != null) {
            for (ExerciseUnit exerciseUnit : exerciseUnits) {
                ExerciseUnit newUnit = new ExerciseUnit();
                newUnit.setRepetitions(exerciseUnit.getRepetitions());
                newUnit.setExercise(exerciseUnit.getExercise());
                newUnit.setProgramPart(parent);
                units.add(newUnit);
            }
        }
        return units;
    }

    public List<ProgressDto> getProgress(Long id, int page, int size) {
        Client client = findById(id);
        return progressRepository.findByClient(client, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdTimestamp")))
                .stream().map(mapper::map).collect(Collectors.toList());
    }

    public List<ChatDto> getChats() {
        Long id = authService.getUserId();
        User user = userService.findById(id);
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
