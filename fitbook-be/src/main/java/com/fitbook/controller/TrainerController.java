package com.fitbook.controller;

import com.fitbook.dto.ChatDto;
import com.fitbook.dto.ClientDto;
import com.fitbook.dto.SearchDto;
import com.fitbook.dto.TrainerDto;
import com.fitbook.service.TrainerService;
import com.fitbook.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    private final Validator validator;

    @Autowired
    public TrainerController(TrainerService trainerService, Validator validator) {
        this.trainerService = trainerService;
        this.validator = validator;
    }

    @GetMapping("/{id}")
    @Secured("ROLE_CLIENT")
    public TrainerDto findTrainer(@PathVariable("id") Long id) {
        return trainerService.findTrainer(id);
    }

    @GetMapping("/{id}/clients")
    @Secured("ROLE_TRAINER")
    public List<ClientDto> findClientsByTrainer(@PathVariable("id") Long id, Principal principal) {
        validator.checkTrainerAccessRights(id, principal);
        return trainerService.findClientsByTrainer(id);
    }

    @PostMapping
    @Secured("ROLE_CLIENT")
    public List<TrainerDto> findTrainers(@RequestBody SearchDto searchDto,
                                         @RequestParam("page") int page, @RequestParam("size") int size) {
        return trainerService.findTrainers(searchDto, page, size);
    }

    @GetMapping("/{trainer_id}/request/{client_id}")
    @Secured("ROLE_CLIENT")
    public Boolean sendRequest(@PathVariable("trainer_id") Long trainerId,
                               @PathVariable("client_id") Long clientId) {
        return trainerService.sendRequest(trainerId, clientId);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_TRAINER")
    public TrainerDto update(@PathVariable("id") Long id, @RequestBody TrainerDto trainerDto) {
        return trainerService.update(id, trainerDto);
    }

    @GetMapping("/{id}/chat_mates")
    @Secured("ROLE_TRAINER")
    public List<ChatDto> getChats(@PathVariable("id") Long id) {
        return trainerService.getChats(id);
    }
}
