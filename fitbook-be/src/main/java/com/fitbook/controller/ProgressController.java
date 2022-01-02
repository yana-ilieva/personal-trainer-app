package com.fitbook.controller;

import com.fitbook.dto.ProgressDto;
import com.fitbook.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressService progressService;

    @Autowired
    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping
    public ProgressDto create(@RequestBody ProgressDto progressDto) {
        return progressService.create(progressDto);
    }

    @PutMapping("/{id}")
    public ProgressDto update(@PathVariable("id") Long id, @RequestBody ProgressDto progressDto) {
         return progressService.update(id, progressDto);
    }
}
