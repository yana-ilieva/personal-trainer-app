package com.fitbook.service;

import com.fitbook.dto.ProgramDto;
import com.fitbook.entity.program.Program;
import com.fitbook.entity.program.ProgramPart;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.ProgramRepository;
import com.fitbook.util.Mapper;
import com.fitbook.util.ProgramPartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    private final Mapper mapper;

    private final ProgramPartUtil programPartUtil;

    private final UserService userService;

    private final TrainerService trainerService;

    @Autowired
    public ProgramService(ProgramRepository programRepository, Mapper mapper, ProgramPartUtil programPartUtil, UserService userService, TrainerService trainerService) {
        this.programRepository = programRepository;
        this.mapper = mapper;
        this.programPartUtil = programPartUtil;
        this.userService = userService;
        this.trainerService = trainerService;
    }

    public Program findById(Long id) {
        Optional<Program> programOpt = programRepository.findById(id);
        if (programOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Failed to find program with id %d", id));
        }

        return programOpt.get();
    }

    public ProgramDto create(ProgramDto programDto, Long trainerUserId) {
        Program program = mapper.map(programDto);

        List<ProgramPart> programParts = program.getProgramParts();

        program.setProgramParts(programPartUtil.transformProgramParts(programParts));
        for (ProgramPart programPart : program.getProgramParts()) {
            programPart.setProgram(program);
        }

        program = programRepository.save(program);

        User user = userService.findById(trainerUserId);
        Trainer trainer = trainerService.findTrainerByUser(user);
        if (trainer.getPrograms() == null) {
            trainer.setPrograms(new ArrayList<>());
        }
        trainer.getPrograms().add(program);

        trainerService.save(trainer);

        return mapper.map(program);
    }

    public Program create(Program program) {
        return programRepository.save(program);
    }

    public ProgramDto update(Long id, ProgramDto programDto) {
        Optional<Program> programOpt = programRepository.findById(id);
        if (programOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Failed to find program with id %d", id));
        }

        Program program = programOpt.get();
        mapper.map(programDto, program);
        program.setId(id);

        return mapper.map(programRepository.save(program));
    }
}
