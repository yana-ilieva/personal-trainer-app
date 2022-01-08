package com.fitbook.entity.trainer;

import com.fitbook.entity.chat.Chat;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.program.Program;
import com.fitbook.entity.user.User;
import com.fitbook.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trainers")
@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String description;

    private String city;

    private String neighborhood;

    @OneToMany(mappedBy = "trainer")
    private List<Client> clients;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "trainer")
    private List<Chat> chats;

    @OneToMany
    private List<Program> programs;
}
