package com.fitbook.controller;

import com.fitbook.dto.ProgramDto;
import com.fitbook.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/program")
@CrossOrigin(origins = "http://localhost:8080")
public class ProgramController {

    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping("/user/{user_id}")
    public ProgramDto save(@PathVariable("user_id") Long userId, @RequestBody ProgramDto programDto) {
        return programService.create(programDto, userId);
    }

    @PutMapping("/{id}")
    public ProgramDto update(@PathVariable("id") Long id, @RequestBody ProgramDto programDto) {
        return programService.update(id, programDto);
    }
}
