package com.fitbook.service;

import com.fitbook.dto.RegistrationDto;
import com.fitbook.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;

    @Autowired
    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public void register(RegistrationDto registrationDto) {
        UserDto userDto = UserDto.builder()
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .role(registrationDto.getRole())
                .build();

        userService.save(userDto);
    }
}
