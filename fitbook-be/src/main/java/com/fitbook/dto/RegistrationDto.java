package com.fitbook.dto;

import lombok.Data;

@Data
public class RegistrationDto {

    private String email;

    private String password;

    private String role;
}
