package com.fitbook.controller;

import com.fitbook.dto.ChatDto;
import com.fitbook.dto.ClientDto;
import com.fitbook.dto.ProgressDto;
import com.fitbook.service.ClientService;
import com.fitbook.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "http://localhost:8080")
public class ClientController {

    private final ClientService clientService;

    private final Validator validator;

    @Autowired
    public ClientController(ClientService clientService, Validator validator) {
        this.clientService = clientService;
        this.validator = validator;
    }

    @GetMapping("/{id}/progress")
    @Secured("ROLE_CLIENT")
    public List<ProgressDto> getProgress(@PathVariable("id") Long id,
                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "20") int size,
                                         Authentication authentication) {
        validator.checkClientAccessRights(id, authentication);
        return clientService.getProgress(id, page, size);
    }

    @GetMapping("/user/{user_id}")
    @Secured("ROLE_CLIENT")
    public ClientDto findClientByUserId(@PathVariable("user_id") Long id) {
        return clientService.findClientByUserId(id);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_CLIENT")
    public ClientDto update(@PathVariable("id") Long id, @RequestBody ClientDto clientDto) {
        return clientService.update(id, clientDto);
    }

    @GetMapping("/{client_id}/program/{program_id}")
    @Secured("ROLE_TRAINER")
    public ClientDto assignProgramToClient(@PathVariable("client_id") Long clientId, @PathVariable("program_id") Long programId) {
        return clientService.assignProgramToClient(clientId, programId);
    }

    @GetMapping("/{client_id}/nutrition_plan/{nutrition_plan_id}")
    @Secured("ROLE_TRAINER")
    public ClientDto assignNutritionPlanToClient(@PathVariable("client_id") Long clientId, @PathVariable("nutrition_plan_id") Long nutritionPlanId) {
        return clientService.assignNutritionPlanToClient(clientId, nutritionPlanId);
    }

    @GetMapping("/{id}/chat_mates")
    @Secured("ROLE_CLIENT")
    public List<ChatDto> getChats(@PathVariable("id") Long id) {
        return clientService.getChats(id);
    }
}
