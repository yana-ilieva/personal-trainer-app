package com.fitbook.dto;

import com.fitbook.enums.WeekDay;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProgramPartDto {

    private Long id;

    private WeekDay weekDay;

    private Integer restBetweenExercises;

    private List<ExerciseUnitDto> exerciseUnits;
}
