package com.fitbook.dto;

import com.fitbook.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientFullDto {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private Gender gender;

    private Integer height;

    private String description;

    private ProgramDto programDto;

    private NutritionPlanDto nutritionPlanDto;


}
