package com.fitbook.entity.client;

import com.fitbook.entity.chat.Chat;
import com.fitbook.entity.program.NutritionPlan;
import com.fitbook.entity.program.Program;
import com.fitbook.entity.trainer.Trainer;
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
@Table(name = "clients")
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private Integer height;

    private String description;

    @ManyToOne
    private Trainer trainer;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    private Program program;

    @OneToOne
    @JoinColumn(name = "nutrition_plan_id", referencedColumnName = "id")
    private NutritionPlan nutritionPlan;

    @OneToMany(mappedBy = "client")
    private List<Progress> progresses;

    @OneToMany(mappedBy = "client")
    private List<Chat> chats;
}
