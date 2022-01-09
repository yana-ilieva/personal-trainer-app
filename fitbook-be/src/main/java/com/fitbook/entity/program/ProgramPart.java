package com.fitbook.entity.program;

import com.fitbook.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "program_parts")
@Entity
public class ProgramPart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private WeekDay weekDay;

    @OneToMany(mappedBy = "programPart", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<ExerciseUnit> exerciseUnits;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
}
