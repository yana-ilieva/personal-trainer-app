package com.fitbook.service;

import com.fitbook.dto.ExerciseDto;
import com.fitbook.dto.ExerciseUnitDto;
import com.fitbook.dto.ProgramDto;
import com.fitbook.dto.ProgramPartDto;
import com.fitbook.entity.program.Program;
import com.fitbook.entity.program.ProgramPart;
import com.fitbook.enums.WeekDay;
import com.fitbook.repository.ProgramRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/exercises-clear.sql", "/exercises.sql"})
public class ProgramServiceTest {

    @Autowired
    private ProgramService programService;

    @Test
    @Transactional
    public void create() {
        ExerciseDto exerciseDto = exerciseDto(10000L, "first exercise");
        ExerciseDto exerciseDto2 = exerciseDto(10001L, "second exercise");
        ExerciseDto exerciseDto3 = exerciseDto(10002L, "third exercise");
        ExerciseDto exerciseDto4 = exerciseDto(10003L, "fourth exercise");

        ExerciseUnitDto exerciseUnitDto = exerciseUnitDto(10, exerciseDto);
        ExerciseUnitDto exerciseUnitDto2 = exerciseUnitDto(10, exerciseDto2);
        ExerciseUnitDto exerciseUnitDto3 = exerciseUnitDto(10, exerciseDto3);
        ExerciseUnitDto exerciseUnitDto4 = exerciseUnitDto(10, exerciseDto4);

        ProgramPartDto programPartDto = programPartDto(WeekDay.MONDAY, 2, List.of(exerciseUnitDto, exerciseUnitDto2));
        ProgramPartDto programPartDto2 = programPartDto(WeekDay.WEDNESDAY, 2, List.of(exerciseUnitDto3, exerciseUnitDto4));
        ProgramDto programDto = programDto("some description", List.of(programPartDto, programPartDto2));

        ProgramDto saved = programService.create(programDto);

        assertAll(
                () -> assertNotNull(saved),
                () -> assertNotNull(saved.getProgramParts()),
                () -> assertEquals(7, saved.getProgramParts().size()),
                () -> assertNotNull(saved.getProgramParts().get(0).getExerciseUnits()),
                () -> assertEquals(2, saved.getProgramParts().get(0).getExerciseUnits().size()),
                () -> assertNotNull(saved.getProgramParts().get(0).getExerciseUnits().get(0).getExercise()),
                () -> assertEquals("some description", saved.getDescription()),
                () -> assertEquals(WeekDay.MONDAY, saved.getProgramParts().get(0).getWeekDay()),
                () -> assertEquals(2, saved.getProgramParts().get(0).getRestBetweenExercises()),
                () -> assertEquals(10, saved.getProgramParts().get(0).getExerciseUnits().get(0).getRepetitions()),
                () -> assertEquals("first exercise", saved.getProgramParts().get(0).getExerciseUnits().get(0).getExercise().getName())
        );
    }

    @Test
    @Transactional
    public void update() {
        ExerciseDto exerciseDto = exerciseDto(10000L, "first exercise");
        ExerciseDto exerciseDto2 = exerciseDto(10001L, "second exercise");
        ExerciseDto exerciseDto3 = exerciseDto(10002L, "third exercise");
        ExerciseDto exerciseDto4 = exerciseDto(10003L, "fourth exercise");

        ExerciseUnitDto exerciseUnitDto = exerciseUnitDto(10, exerciseDto);
        ExerciseUnitDto exerciseUnitDto2 = exerciseUnitDto(10, exerciseDto2);
        ExerciseUnitDto exerciseUnitDto3 = exerciseUnitDto(10, exerciseDto3);
        ExerciseUnitDto exerciseUnitDto4 = exerciseUnitDto(10, exerciseDto4);

        List<ExerciseUnitDto> exerciseUnitDtos = new ArrayList<>();
        exerciseUnitDtos.add(exerciseUnitDto);
        exerciseUnitDtos.add(exerciseUnitDto2);
        ProgramPartDto programPartDto = programPartDto(WeekDay.MONDAY, 2, exerciseUnitDtos);
        List<ExerciseUnitDto> exerciseUnitDtos2 = new ArrayList<>();
        exerciseUnitDtos2.add(exerciseUnitDto3);
        exerciseUnitDtos2.add(exerciseUnitDto4);
        ProgramPartDto programPartDto2 = programPartDto(WeekDay.WEDNESDAY,2, exerciseUnitDtos2);
        List<ProgramPartDto> programPartDtos = new ArrayList<>();
        programPartDtos.add(programPartDto);
        programPartDtos.add(programPartDto2);

        ProgramDto programDto = programDto("some description", programPartDtos);

        ProgramDto saved = programService.create(programDto);

        ExerciseDto newExercise = exerciseDto(10004L, "fifth exercise");

        ExerciseUnitDto newExerciseUnit = exerciseUnitDto(15, newExercise);

        saved.getProgramParts().get(0).getExerciseUnits().add(newExerciseUnit);

        ProgramDto updated = programService.update(saved.getId(), saved);

        assertAll(
                () -> assertEquals(3, updated.getProgramParts().get(0).getExerciseUnits().size()),
                () -> assertEquals("fifth exercise", updated.getProgramParts().get(0).getExerciseUnits().get(2).getExercise().getName())
        );
    }

    private ExerciseDto exerciseDto(Long id, String name) {
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setId(id);
        exerciseDto.setName(name);
        return exerciseDto;
    }

    private ExerciseUnitDto exerciseUnitDto(Integer reps, ExerciseDto exerciseDto) {
        ExerciseUnitDto exerciseUnitDto = new ExerciseUnitDto();
        exerciseUnitDto.setRepetitions(reps);
        exerciseUnitDto.setExercise(exerciseDto);
        return exerciseUnitDto;
    }

    private ProgramPartDto programPartDto(WeekDay weekDay, Integer rest, List<ExerciseUnitDto> exerciseUnitDtos) {
        ProgramPartDto programPartDto = new ProgramPartDto();
        programPartDto.setWeekDay(weekDay);
        programPartDto.setRestBetweenExercises(rest);
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