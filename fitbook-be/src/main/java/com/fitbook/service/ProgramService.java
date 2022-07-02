package com.fitbook.service;

import com.fitbook.dto.ProgramDto;
import com.fitbook.entity.program.Exercise;
import com.fitbook.entity.program.ExerciseUnit;
import com.fitbook.entity.program.Program;
import com.fitbook.entity.program.ProgramPart;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import com.fitbook.exception.RequestProcessingException;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.ExerciseRepository;
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

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ProgramService(ProgramRepository programRepository, Mapper mapper, ProgramPartUtil programPartUtil, UserService userService,
                          TrainerService trainerService, ExerciseRepository exerciseRepository) {
        this.programRepository = programRepository;
        this.mapper = mapper;
        this.programPartUtil = programPartUtil;
        this.userService = userService;
        this.trainerService = trainerService;
        this.exerciseRepository = exerciseRepository;
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

        if (program == null) {
            throw new RequestProcessingException("Program could not be created");
        }

        if (program.getProgramParts() != null) {
            for (int i = 0; i < program.getProgramParts().size(); i++) {
                if (program.getProgramParts().get(i) != null) {
                    if (program.getProgramParts().get(i).getExerciseUnits() != null) {
                        for (int i1 = 0; i1 < program.getProgramParts().get(i).getExerciseUnits().size(); i1++) {
                            if (program.getProgramParts().get(i).getExerciseUnits().get(i1) != null) {
                                if (program.getProgramParts().get(i).getExerciseUnits().get(i1).getExercise().getId() == null) {
                                    Optional<Exercise> exerciseOpt = exerciseRepository.findByName(program.getProgramParts().get(i).getExerciseUnits().get(i1).getExercise().getName());
                                    if (exerciseOpt.isPresent()) {
                                        program.getProgramParts().get(i).getExerciseUnits().get(i1).getExercise().setId(exerciseOpt.get().getId());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        List<ProgramPart> programParts = program.getProgramParts();

        if (programParts != null) {
            program.setProgramParts(programPartUtil.transformProgramParts(programParts));
        }
        if (program.getProgramParts() != null) {
            for (ProgramPart programPart : program.getProgramParts()) {
                programPart.setProgram(program);
                if (programPart.getExerciseUnits() != null) {
                    for (ExerciseUnit exerciseUnit : programPart.getExerciseUnits()) {
                        exerciseUnit.setProgramPart(programPart);
                    }
                }
            }
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

        if (program.getProgramParts() != null) {
            for (int i = 0; i < program.getProgramParts().size(); i++) {
                if (program.getProgramParts().get(i) != null) {
                    if (program.getProgramParts().get(i).getExerciseUnits() != null) {
                        for (int i1 = 0; i1 < program.getProgramParts().get(i).getExerciseUnits().size(); i1++) {
                            if (program.getProgramParts().get(i).getExerciseUnits().get(i1) != null) {
                                if (program.getProgramParts().get(i).getExerciseUnits().get(i1).getExercise().getId() == null) {
                                    Optional<Exercise> exerciseOpt = exerciseRepository.findByName(program.getProgramParts().get(i).getExerciseUnits().get(i1).getExercise().getName());
                                    if (exerciseOpt.isPresent()) {
                                        program.getProgramParts().get(i).getExerciseUnits().get(i1).getExercise().setId(exerciseOpt.get().getId());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (program.getProgramParts() != null) {
            for (ProgramPart programPart : program.getProgramParts()) {
                programPart.setProgram(program);
                if (programPart.getExerciseUnits() != null) {
                    for (ExerciseUnit exerciseUnit : programPart.getExerciseUnits()) {
                        exerciseUnit.setProgramPart(programPart);
                    }
                }
            }
        }

        return mapper.map(programRepository.save(program));
    }
}
