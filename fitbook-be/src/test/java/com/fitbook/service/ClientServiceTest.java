package com.fitbook.service;

import com.fitbook.dto.ClientDto;
import com.fitbook.dto.ClientFullDto;
import com.fitbook.dto.ProgramDto;
import com.fitbook.dto.RegistrationDto;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.user.User;
import com.fitbook.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/trainers-clear.sql", "/trainers.sql"})
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Test
    public void create() {
        User user = userService.findById(1L);
        RegistrationDto dto = new RegistrationDto();
        dto.setGender(Gender.MALE);
        dto.setFirstName("Test");
        dto.setLastName("Testsson");
        Client client = clientService.create(user, dto);

        assertEquals(Gender.MALE, client.getGender());
        assertEquals("Test", client.getFirstName());
        assertEquals("Testsson", client.getLastName());
    }

    @Test
    public void update() {
        User user = userService.findById(1L);
        RegistrationDto dto = new RegistrationDto();
        dto.setGender(Gender.MALE);
        dto.setFirstName("Test");
        dto.setLastName("Testsson");
        Client client = clientService.create(user, dto);

        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName("New");
        clientDto.setLastName("NewName");
        clientDto.setGender(Gender.MALE);

        ClientDto update = clientService.update(client.getId(), clientDto);

        assertEquals(Gender.MALE, update.getGender());
        assertEquals("New", update.getFirstName());
        assertEquals("NewName", update.getLastName());
    }

    @Test
    @Transactional
    public void assignProgramToClient() {
        ClientDto clientDto = clientService.assignProgramToClient(10L, 10L);
        ClientFullDto fullDto = clientService.getFullDto(clientDto.getId());
        assertNotNull(fullDto.getProgramDto());
    }
}