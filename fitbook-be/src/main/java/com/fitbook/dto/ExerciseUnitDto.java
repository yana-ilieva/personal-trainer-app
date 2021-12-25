package com.fitbook.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseUnitDto {

    private Long id;

    private Integer repetitions;

    private ExerciseDto exercise;
}
