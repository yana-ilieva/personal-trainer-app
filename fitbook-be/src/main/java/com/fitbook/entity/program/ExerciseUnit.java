package com.fitbook.entity.program;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "exercise_unit")
@Data
@NoArgsConstructor
public class ExerciseUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer repetitions;

    @OneToOne
    private Exercise exercise;
}
