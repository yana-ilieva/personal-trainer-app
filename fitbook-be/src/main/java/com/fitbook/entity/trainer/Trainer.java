package com.fitbook.entity.trainer;

import com.fitbook.entity.client.Client;
import com.fitbook.entity.user.User;
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
@Table(name = "trainers")
@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private Gender gender;

    private String description;

    private String city;

    private String neighborhood;

    @OneToMany
    private List<Client> clients;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
