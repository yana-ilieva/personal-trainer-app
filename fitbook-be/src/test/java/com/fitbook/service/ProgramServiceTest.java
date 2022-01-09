package com.fitbook.service;

import com.fitbook.dto.ExerciseDto;
import com.fitbook.dto.ExerciseUnitDto;
import com.fitbook.dto.ProgramDto;
import com.fitbook.dto.ProgramPartDto;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.enums.WeekDay;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Sql(scripts = {"/exercises-clear.sql", "/exercises.sql"})
public class ProgramServiceTest {

    @Autowired
    private ProgramService programService;

    @MockBean
    private TrainerService trainerService;

    @Test
    @Transactional
    public void create() {
        Mockito.when(trainerService.save(any())).thenReturn(new Trainer());
        Mockito.when(trainerService.findTrainerByUser(any())).thenReturn(new Trainer());
        ExerciseDto exerciseDto = exerciseDto(10000L, "first exercise");
        ExerciseDto exerciseDto2 = exerciseDto(10001L, "second exercise");
        ExerciseDto exerciseDto3 = exerciseDto(10002L, "third exercise");
        ExerciseDto exerciseDto4 = exerciseDto(10003L, "fourth exercise");

        ExerciseUnitDto exerciseUnitDto = exerciseUnitDto(10, exerciseDto, 2);
        ExerciseUnitDto exerciseUnitDto2 = exerciseUnitDto(10, exerciseDto2, 2);
        ExerciseUnitDto exerciseUnitDto3 = exerciseUnitDto(10, exerciseDto3, 2);
        ExerciseUnitDto exerciseUnitDto4 = exerciseUnitDto(10, exerciseDto4, 2);

        ProgramPartDto programPartDto = programPartDto(WeekDay.MONDAY, List.of(exerciseUnitDto, exerciseUnitDto2));
        ProgramPartDto programPartDto2 = programPartDto(WeekDay.WEDNESDAY, List.of(exerciseUnitDto3, exerciseUnitDto4));
        ProgramDto programDto = programDto("some description", List.of(programPartDto, programPartDto2));

        ProgramDto saved = programService.create(programDto, 10000L);

        assertAll(
                () -> assertNotNull(saved),
                () -> assertNotNull(saved.getProgramParts()),
                () -> assertEquals(7, saved.getProgramParts().size()),
                () -> assertNotNull(saved.getProgramParts().get(0).getExerciseUnits()),
                () -> assertEquals(2, saved.getProgramParts().get(0).getExerciseUnits().size()),
                () -> assertNotNull(saved.getProgramParts().get(0).getExerciseUnits().get(0).getExercise()),
                () -> assertEquals("some description", saved.getDescription()),
                () -> assertEquals(WeekDay.MONDAY, saved.getProgramParts().get(0).getWeekDay()),
                () -> assertEquals(10, saved.getProgramParts().get(0).getExerciseUnits().get(0).getRepetitions()),
                () -> assertEquals("first exercise", saved.getProgramParts().get(0).getExerciseUnits().get(0).getExercise().getName())
        );
    }

    @Test
    @Transactional
    public void update_addNewExerciseUnit() {
        Mockito.when(trainerService.save(any())).thenReturn(new Trainer());
        Mockito.when(trainerService.findTrainerByUser(any())).thenReturn(new Trainer());
        ExerciseDto exerciseDto = exerciseDto(10000L, "first exercise");
        ExerciseDto exerciseDto2 = exerciseDto(10001L, "second exercise");
        ExerciseDto exerciseDto3 = exerciseDto(10002L, "third exercise");
        ExerciseDto exerciseDto4 = exerciseDto(10003L, "fourth exercise");

        ExerciseUnitDto exerciseUnitDto = exerciseUnitDto(10, exerciseDto, 2);
        ExerciseUnitDto exerciseUnitDto2 = exerciseUnitDto(10, exerciseDto2, 2);
        ExerciseUnitDto exerciseUnitDto3 = exerciseUnitDto(10, exerciseDto3, 2);
        ExerciseUnitDto exerciseUnitDto4 = exerciseUnitDto(10, exerciseDto4, 2);

        List<ExerciseUnitDto> exerciseUnitDtos = new ArrayList<>();
        exerciseUnitDtos.add(exerciseUnitDto);
        exerciseUnitDtos.add(exerciseUnitDto2);
        ProgramPartDto programPartDto = programPartDto(WeekDay.MONDAY, exerciseUnitDtos);
        List<ExerciseUnitDto> exerciseUnitDtos2 = new ArrayList<>();
        exerciseUnitDtos2.add(exerciseUnitDto3);
        exerciseUnitDtos2.add(exerciseUnitDto4);
        ProgramPartDto programPartDto2 = programPartDto(WeekDay.WEDNESDAY, exerciseUnitDtos2);
        List<ProgramPartDto> programPartDtos = new ArrayList<>();
        programPartDtos.add(programPartDto);
        programPartDtos.add(programPartDto2);

        ProgramDto programDto = programDto("some description", programPartDtos);

        ProgramDto saved = programService.create(programDto, 10000L);

        ExerciseDto newExercise = exerciseDto(10004L, "fifth exercise");

        ExerciseUnitDto newExerciseUnit = exerciseUnitDto(15, newExercise, 2);

        saved.getProgramParts().get(0).getExerciseUnits().add(newExerciseUnit);

        ProgramDto updated = programService.update(saved.getId(), saved);

        assertAll(
                () -> assertEquals(3, updated.getProgramParts().get(0).getExerciseUnits().size()),
                () -> assertEquals("fifth exercise", updated.getProgramParts().get(0).getExerciseUnits().get(2).getExercise().getName())
        );
    }

    @Test
    @Transactional
    public void update_removeExerciseUnit() {
        Mockito.when(trainerService.save(any())).thenReturn(new Trainer());
        Mockito.when(trainerService.findTrainerByUser(any())).thenReturn(new Trainer());
        ExerciseDto exerciseDto = exerciseDto(10000L, "first exercise");
        ExerciseDto exerciseDto2 = exerciseDto(10001L, "second exercise");
        ExerciseDto exerciseDto3 = exerciseDto(10002L, "third exercise");
        ExerciseDto exerciseDto4 = exerciseDto(10003L, "fourth exercise");

        ExerciseUnitDto exerciseUnitDto = exerciseUnitDto(10, exerciseDto, 2);
        ExerciseUnitDto exerciseUnitDto2 = exerciseUnitDto(10, exerciseDto2, 2);
        ExerciseUnitDto exerciseUnitDto3 = exerciseUnitDto(10, exerciseDto3, 2);
        ExerciseUnitDto exerciseUnitDto4 = exerciseUnitDto(10, exerciseDto4, 2);

        List<ExerciseUnitDto> exerciseUnitDtos = new ArrayList<>();
        exerciseUnitDtos.add(exerciseUnitDto);
        exerciseUnitDtos.add(exerciseUnitDto2);
        ProgramPartDto programPartDto = programPartDto(WeekDay.MONDAY, exerciseUnitDtos);
        List<ExerciseUnitDto> exerciseUnitDtos2 = new ArrayList<>();
        exerciseUnitDtos2.add(exerciseUnitDto3);
        exerciseUnitDtos2.add(exerciseUnitDto4);
        ProgramPartDto programPartDto2 = programPartDto(WeekDay.WEDNESDAY, exerciseUnitDtos2);
        List<ProgramPartDto> programPartDtos = new ArrayList<>();
        programPartDtos.add(programPartDto);
        programPartDtos.add(programPartDto2);

        ProgramDto programDto = programDto("some description", programPartDtos);

        ProgramDto saved = programService.create(programDto, 10000L);

        saved.getProgramParts().get(0).getExerciseUnits().remove(1);

        ProgramDto updated = programService.update(saved.getId(), saved);

        assertAll(
                () -> assertEquals(1, updated.getProgramParts().get(0).getExerciseUnits().size()),
                () -> assertEquals("first exercise", updated.getProgramParts().get(0).getExerciseUnits().get(0).getExercise().getName())
        );
    }

    private ExerciseDto exerciseDto(Long id, String name) {
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setId(id);
        exerciseDto.setName(name);
        return exerciseDto;
    }

    private ExerciseUnitDto exerciseUnitDto(Integer reps, ExerciseDto exerciseDto, Integer rest) {
        ExerciseUnitDto exerciseUnitDto = new ExerciseUnitDto();
        exerciseUnitDto.setRepetitions(reps);
        exerciseUnitDto.setExercise(exerciseDto);
        exerciseUnitDto.setRestBetweenExercises(rest);
        return exerciseUnitDto;
    }

    private ProgramPartDto programPartDto(WeekDay weekDay, List<ExerciseUnitDto> exerciseUnitDtos) {
        ProgramPartDto programPartDto = new ProgramPartDto();
        programPartDto.setWeekDay(weekDay);
        programPartDto.setExerciseUnits(exerciseUnitDtos);
        return programPartDto;
    }

    private ProgramDto programDto(String description, List<ProgramPartDto> programPartDtos) {
        ProgramDto programDto = new ProgramDto();
        programDto.setDescription(description);
        programDto.setProgramParts(programPartDtos);
        return programDto;
    }
}