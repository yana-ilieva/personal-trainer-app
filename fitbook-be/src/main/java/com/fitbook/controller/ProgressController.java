package com.fitbook.controller;

import com.fitbook.dto.ProgressDto;
import com.fitbook.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "http://localhost:8080")
public class ProgressController {

    private final ProgressService progressService;

    @Autowired
    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping
    @Secured("ROLE_CLIENT")
    public ProgressDto create(@RequestBody ProgressDto progressDto) {
        return progressService.create(progressDto);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_CLIENT")
    public ProgressDto update(@PathVariable("id") Long id, @RequestBody ProgressDto progressDto) {
         return progressService.update(id, progressDto);
    }
}
