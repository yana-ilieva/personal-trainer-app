package com.fitbook.entity.user;

import com.fitbook.entity.user.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String username;

    private String password;

    @ManyToOne
    private Role role;

    private Boolean deleted;
}
