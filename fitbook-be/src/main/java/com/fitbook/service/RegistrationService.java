package com.fitbook.service;

import com.fitbook.dto.RegistrationDto;
import com.fitbook.dto.UserDto;
import com.fitbook.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;

    private final TrainerService trainerService;

    private final ClientService clientService;

    @Autowired
    public RegistrationService(UserService userService, TrainerService trainerService, ClientService clientService) {
        this.userService = userService;
        this.trainerService = trainerService;
        this.clientService = clientService;
    }

    public void register(RegistrationDto registrationDto) {
        UserDto userDto = UserDto.builder()
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .role(registrationDto.getRole())
                .build();

        User user = userService.save(userDto);
        createEntity(user);
    }

    private void createEntity(User user) {
        if (user.getRole().getName().equals("ROLE_TRAINER")) {
            trainerService.create(user);
        }
        if (user.getRole().getName().equals("ROLE_CLIENT")) {
            clientService.create(user);
        }
    }
}
