package com.fitbook.entity.program;

import com.fitbook.entity.client.Client;
import com.fitbook.entity.trainer.Trainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "programs")
@Entity
public class Program implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "program", cascade = {CascadeType.ALL})
    private List<ProgramPart> programParts;

    private String name;

    private String description;

    @OneToOne(mappedBy = "program")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProgramPart> getProgramParts() {
        return programParts;
    }

    public void setProgramParts(List<ProgramPart> programParts) {
        this.programParts = programParts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
