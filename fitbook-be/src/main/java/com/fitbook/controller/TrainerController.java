package com.fitbook.controller;

import com.fitbook.dto.*;
import com.fitbook.service.TrainerService;
import com.fitbook.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainer")
@CrossOrigin(origins = "http://localhost:8080")
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
    public List<ClientDto> findClientsByTrainer(@PathVariable("id") Long id, Authentication authentication) {
        validator.checkTrainerAccessRights(id, authentication);
        return trainerService.findClientsByTrainer(id);
    }

    @GetMapping("/{id}/programs")
    @Secured("ROLE_TRAINER")
    public List<ProgramDto> getPrograms(@PathVariable("id") Long id) {
        return trainerService.findProgramsByUserId(id);
    }

    @GetMapping("/user/{user_id}")
    @Secured("ROLE_TRAINER")
    public TrainerDto findTrainerByUserId(@PathVariable("user_id") Long id) {
        return trainerService.findTrainerByUserId(id);
    }

    @PostMapping
    @Secured("ROLE_CLIENT")
    public List<TrainerDto> findTrainers(@RequestBody SearchDto searchDto,
                                         @RequestParam("page") int page, @RequestParam("size") int size) {
        return trainerService.findTrainers(searchDto, page, size);
    }

    @GetMapping("/{trainer_id}/request/user/{user_id}")
    @Secured("ROLE_CLIENT")
    public Boolean sendRequest(@PathVariable("trainer_id") Long trainerId,
                               @PathVariable("user_id") Long userId) {
        return trainerService.sendRequest(trainerId, userId);
    }

    @GetMapping("/user/{user_id}/handle_request/{client_id}")
    @Secured("ROLE_TRAINER")
    public Boolean handleRequest(@PathVariable("user_id") Long userId,
                                 @PathVariable("client_id") Long clientId) {
        return trainerService.handleRequest(userId, clientId);
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

    @DeleteMapping("/{trainer_id}/client/{client_id}")
    @Secured("ROLE_TRAINER")
    public boolean removeClientFromList(@PathVariable("trainer_id") Long trainerId,
                                        @PathVariable("client_id") Long clientId, Authentication authentication) {
        validator.checkTrainerAccessRights(trainerId, authentication);
        return trainerService.removeClientFromList(clientId);
    }
}
