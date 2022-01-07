package com.fitbook.controller;

import com.fitbook.dto.RegistrationDto;
import com.fitbook.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
@CrossOrigin(origins = "http://localhost:8080")
public class RegistrationController {

    private final RegistrationService authService;

    @Autowired
    public RegistrationController(RegistrationService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> register(@RequestBody RegistrationDto registrationDto) {
        authService.register(registrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
