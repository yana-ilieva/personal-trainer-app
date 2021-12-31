package com.fitbook.controller;

import com.fitbook.dto.ProgressDto;
import com.fitbook.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressService progressService;

    @Autowired
    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping
    public void create(@RequestBody ProgressDto progressDto) {
       // progressService.
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody ProgressDto progressDto) {
        // progressService.
    }
}
