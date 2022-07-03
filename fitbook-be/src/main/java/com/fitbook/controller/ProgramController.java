package com.fitbook.controller;

import com.fitbook.dto.ProgramDto;
import com.fitbook.service.ProgramService;
import com.fitbook.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/program")
@CrossOrigin(origins = "http://localhost:8080")
public class ProgramController {

    private final ProgramService programService;

    private final Validator validator;

    @Autowired
    public ProgramController(ProgramService programService, Validator validator) {
        this.programService = programService;
        this.validator = validator;
    }

    @PostMapping("/user/{user_id}")
    @Secured("ROLE_TRAINER")
    public ProgramDto save(@PathVariable("user_id") Long userId, @RequestBody ProgramDto programDto) {
        validator.checkTrainerAccessRightsByUser(userId);
        return programService.create(programDto, userId);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_TRAINER")
    public ProgramDto update(@PathVariable("id") Long id, @RequestBody ProgramDto programDto) {
        validator.checkTrainerAccessRightsByProgram(id);
        return programService.update(id, programDto);
    }
}
