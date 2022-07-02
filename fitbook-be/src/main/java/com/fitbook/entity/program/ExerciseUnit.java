package com.fitbook.entity.program;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "exercise_units")
@NoArgsConstructor
public class ExerciseUnit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer repetitions;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "program_part_id")
    private ProgramPart programPart;

    private Integer restBetweenExercises;

    @OneToOne
    private Exercise exercise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public ProgramPart getProgramPart() {
        return programPart;
    }

    public void setProgramPart(ProgramPart programPart) {
        this.programPart = programPart;
    }

    public Integer getRestBetweenExercises() {
        return restBetweenExercises;
    }

    public void setRestBetweenExercises(Integer restBetweenExercises) {
        this.restBetweenExercises = restBetweenExercises;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
