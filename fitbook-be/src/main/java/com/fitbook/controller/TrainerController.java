package com.fitbook.controller;

import com.fitbook.dto.SearchDto;
import com.fitbook.dto.TrainerDto;
import com.fitbook.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping("/{id}")
    @Secured("ROLE_CLIENT")
    public TrainerDto findTrainer(@PathVariable("id") Long id) {
        return trainerService.findTrainer(id);
    }

    @PostMapping
    @Secured("ROLE_CLIENT")
    public List<TrainerDto> findTrainers(@RequestBody SearchDto searchDto,
                                         @RequestParam("page") int page, @RequestParam("size") int size) {
        return trainerService.findTrainers(searchDto, page, size);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_TRAINER")
    public TrainerDto update(@PathVariable("id") Long id, @RequestBody TrainerDto trainerDto) {
        return trainerService.update(id, trainerDto);
    }
}
