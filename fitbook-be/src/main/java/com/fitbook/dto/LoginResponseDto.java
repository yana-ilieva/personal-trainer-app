package com.fitbook.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String jwt;

    private String role;

    private Long userId;
}
