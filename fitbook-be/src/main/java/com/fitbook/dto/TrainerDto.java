package com.fitbook.dto;

import com.fitbook.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private Gender gender;

    private String description;

    private String city;

    private String neighborhood;
}
