package com.fitbook.entity.program;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "exercise_units")
@Data
@NoArgsConstructor
public class ExerciseUnit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer repetitions;

    @ManyToOne
    @JoinColumn(name = "program_part_id")
    private ProgramPart programPart;

    private Integer restBetweenExercises;

    @OneToOne
    private Exercise exercise;
}
