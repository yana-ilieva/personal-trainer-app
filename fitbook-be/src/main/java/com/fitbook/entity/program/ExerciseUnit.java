package com.fitbook.entity.program;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "exercise_units")
@Data
@NoArgsConstructor
public class ExerciseUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer repetitions;

    @ManyToOne
    @JoinColumn(name = "program_part_id")
    private ProgramPart programPart;

    @OneToOne
    private Exercise exercise;
}
