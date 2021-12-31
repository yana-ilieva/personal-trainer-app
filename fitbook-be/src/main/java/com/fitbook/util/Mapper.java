package com.fitbook.util;

import com.fitbook.dto.*;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.client.Progress;
import com.fitbook.entity.program.Exercise;
import com.fitbook.entity.program.ExerciseUnit;
import com.fitbook.entity.program.Program;
import com.fitbook.entity.program.ProgramPart;
import com.fitbook.entity.trainer.Trainer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {

    public TrainerDto map(Trainer trainer) {
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setId(trainer.getId());
        trainerDto.setFirstName(trainer.getFirstName());
        trainerDto.setLastName(trainer.getLastName());
        trainerDto.setBirthDate(trainer.getBirthDate());
        trainerDto.setGender(trainer.getGender());
        trainerDto.setCity(trainer.getCity());
        trainerDto.setNeighborhood(trainer.getNeighborhood());
        trainerDto.setDescription(trainerDto.getDescription());
        return trainerDto;
    }

    public void map(Trainer trainer, TrainerDto trainerDto) {
        trainer.setFirstName(trainerDto.getFirstName());
        trainer.setLastName(trainerDto.getLastName());
        trainer.setBirthDate(trainerDto.getBirthDate());
        trainer.setGender(trainerDto.getGender());
        trainer.setCity(trainerDto.getCity());
        trainer.setNeighborhood(trainerDto.getNeighborhood());
        trainer.setDescription(trainerDto.getDescription());
    }

    public void map(Client client, ClientDto clientDto) {
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setBirthDate(clientDto.getBirthDate());
        client.setGender(clientDto.getGender());
        client.setHeight(clientDto.getHeight());
        client.setDescription(clientDto.getDescription());
    }

    public ClientDto map(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(clientDto.getId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setBirthDate(client.getBirthDate());
        clientDto.setGender(client.getGender());
        clientDto.setHeight(client.getHeight());
        clientDto.setDescription(client.getDescription());
        return clientDto;
    }

    public Program map(ProgramDto programDto) {
        Program program = new Program();
        program.setDescription(programDto.getDescription());
        if (programDto.getProgramParts() != null) {
            program.setProgramParts(programDto.getProgramParts().stream().map(this::map).collect(Collectors.toList()));
        }
        return program;
    }

    private ProgramPart map(ProgramPartDto programPartDto) {
        ProgramPart programPart = new ProgramPart();
        programPart.setId(programPartDto.getId());
        programPart.setWeekDay(programPartDto.getWeekDay());
        programPart.setRestBetweenExercises(programPartDto.getRestBetweenExercises());
        if (programPartDto.getExerciseUnits() != null) {
            programPart.setExerciseUnits(programPartDto.getExerciseUnits().stream().map(this::map).collect(Collectors.toList()));
        }
        return programPart;
    }

    private ExerciseUnit map(ExerciseUnitDto exerciseUnitDto) {
        ExerciseUnit exerciseUnit = new ExerciseUnit();
        exerciseUnit.setId(exerciseUnitDto.getId());
        exerciseUnit.setRepetitions(exerciseUnitDto.getRepetitions());
        if (exerciseUnitDto.getExercise() != null) {
            exerciseUnit.setExercise(map(exerciseUnitDto.getExercise()));
        }
        return exerciseUnit;
    }

    private Exercise map(ExerciseDto exerciseDto) {
        Exercise exercise = new Exercise();
        exercise.setId(exerciseDto.getId());
        exercise.setName(exerciseDto.getName());
        return exercise;
    }

    public ProgramDto map(Program program) {
        ProgramDto programDto = new ProgramDto();
        programDto.setId(program.getId());
        programDto.setDescription(program.getDescription());
        if (program.getProgramParts() != null) {
            programDto.setProgramParts(program.getProgramParts().stream().map(this::map).collect(Collectors.toList()));
        }
        return programDto;
    }

    private ProgramPartDto map(ProgramPart programPart) {
        ProgramPartDto programPartDto = new ProgramPartDto();
        programPartDto.setId(programPart.getId());
        programPartDto.setWeekDay(programPart.getWeekDay());
        programPartDto.setRestBetweenExercises(programPart.getRestBetweenExercises());
        if (programPart.getExerciseUnits() != null) {
            programPartDto.setExerciseUnits(programPart.getExerciseUnits().stream().map(this::map).collect(Collectors.toList()));
        }
        return programPartDto;
    }

    private ExerciseUnitDto map(ExerciseUnit exerciseUnit) {
        ExerciseUnitDto exerciseUnitDto = new ExerciseUnitDto();
        exerciseUnitDto.setId(exerciseUnit.getId());
        exerciseUnitDto.setRepetitions(exerciseUnit.getRepetitions());
        if (exerciseUnit.getExercise() != null) {
            exerciseUnitDto.setExercise(map(exerciseUnit.getExercise()));
        }
        return exerciseUnitDto;
    }

    private ExerciseDto map(Exercise exercise) {
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setId(exercise.getId());
        exerciseDto.setName(exercise.getName());
        return exerciseDto;
    }

    public ProgressDto map(Progress progress) {
        ProgressDto progressDto = new ProgressDto();
        progressDto.setId(progress.getId());
        progressDto.setCreatedTimestamp(progress.getCreatedTimestamp());
        return progressDto;
    }

    public void map(ProgramDto programDto, Program program) {
        program.setId(programDto.getId());
        program.setDescription(programDto.getDescription());
        if (programDto.getProgramParts() != null) {
            program.setProgramParts(programDto.getProgramParts().stream().map(this::map).collect(Collectors.toList()));
        }
    }

    private void map(ProgramPartDto programPartDto, ProgramPart programPart) {
        programPart.setId(programPartDto.getId());
        programPart.setWeekDay(programPartDto.getWeekDay());
        programPart.setRestBetweenExercises(programPartDto.getRestBetweenExercises());
        if (programPartDto.getExerciseUnits() != null) {
            programPart.setExerciseUnits(programPartDto.getExerciseUnits().stream().map(this::map).collect(Collectors.toList()));
        }
    }

    private void map(ExerciseUnitDto exerciseUnitDto, ExerciseUnit exerciseUnit) {
        exerciseUnit.setId(exerciseUnitDto.getId());
        exerciseUnit.setRepetitions(exerciseUnitDto.getRepetitions());
        if (exerciseUnitDto.getExercise() != null) {
            exerciseUnit.setExercise(map(exerciseUnitDto.getExercise()));
        }
    }

    private void map(ExerciseDto exerciseDto, Exercise exercise) {
        exercise.setId(exercise.getId());
        exerciseDto.setName(exercise.getName());
    }
}
