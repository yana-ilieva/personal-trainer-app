package com.fitbook.entity.program;

import com.fitbook.entity.trainer.Trainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "programs")
@Entity
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "program", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ProgramPart> programParts;

    private String name;

    private String description;

    public Program(Program program) {
        this.id = null;
        this.description = program.description;
        this.name = program.name;
        this.programParts = program.programParts;
    }
}
