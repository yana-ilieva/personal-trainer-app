package com.fitbook.entity.program;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fitbook.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "program_parts")
@Entity
public class ProgramPart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private WeekDay weekDay;

    @OneToMany(mappedBy = "programPart", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<ExerciseUnit> exerciseUnits;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public List<ExerciseUnit> getExerciseUnits() {
        return exerciseUnits;
    }

    public void setExerciseUnits(List<ExerciseUnit> exerciseUnits) {
        this.exerciseUnits = exerciseUnits;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
