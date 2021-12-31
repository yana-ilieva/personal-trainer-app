package com.fitbook.controller;

import com.fitbook.dto.ClientDto;
import com.fitbook.dto.ProgressDto;
import com.fitbook.service.ClientService;
import com.fitbook.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/client")
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
                                         Principal principal) {
        validator.checkClientAccessRights(id, principal);
        return clientService.getProgress(id, page, size);
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
}
