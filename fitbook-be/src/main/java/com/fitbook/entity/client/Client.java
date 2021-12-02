package com.fitbook.entity.client;

import com.fitbook.entity.trainer.Trainer;
import com.fitbook.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "client")
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private Gender gender;

    private Integer height;

    private String description;

    @ManyToOne
    private Trainer trainer;

    @OneToMany
    private List<Trainer> chatHistory;
}
