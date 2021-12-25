package com.fitbook.entity.program;

import com.fitbook.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "program_parts")
@Entity
public class ProgramPart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private WeekDay weekDay;

    private Integer restBetweenExercises;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ExerciseUnit> exerciseUnits;
}
