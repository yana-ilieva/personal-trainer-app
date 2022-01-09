package com.fitbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseUnitDto {

    private Long id;

    private Integer repetitions;

    private ExerciseDto exercise;

    private Integer restBetweenExercises;
}
