package com.fitbook.dto;

import com.fitbook.enums.Gender;
import lombok.Data;

@Data
public class RegistrationDto {

    private String email;

    private String password;

    private String role;

    private String firstName;

    private String lastName;

    private String city;

    private Gender gender;
}
