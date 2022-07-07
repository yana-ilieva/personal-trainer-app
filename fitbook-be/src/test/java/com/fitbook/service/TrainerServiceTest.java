package com.fitbook.service;

import com.fitbook.dto.ClientDto;
import com.fitbook.dto.RegistrationDto;
import com.fitbook.dto.SearchDto;
import com.fitbook.dto.TrainerDto;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import com.fitbook.enums.Gender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Sql(scripts = {"/trainers-clear.sql", "/trainers.sql"})
public class TrainerServiceTest {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private UserService userService;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private AuthService authService;

    @Test
    public void findTrainers_gender() {
        SearchDto searchDto = new SearchDto();
        searchDto.setGender(Gender.MALE);
        List<TrainerDto> trainers = trainerService.findTrainers(searchDto, 0, 10);
        assertEquals(1, trainers.size());
        assertEquals(Gender.MALE, trainers.get(0).getGender());
    }

    @Test
    public void findTrainers_name() {
        SearchDto searchDto = new SearchDto();
        searchDto.setName("Doe");
        List<TrainerDto> trainers = trainerService.findTrainers(searchDto, 0, 10);
        assertEquals(1, trainers.size());
        assertEquals("Jane", trainers.get(0).getFirstName());
        assertEquals("Doe", trainers.get(0).getLastName());
    }

    @Test
    public void findTrainers_city() {
        SearchDto searchDto = new SearchDto();
        searchDto.setCity("Reading");
        List<TrainerDto> trainers = trainerService.findTrainers(searchDto, 0, 10);
        assertEquals(1, trainers.size());
        assertEquals("Reading", trainers.get(0).getCity());
    }

    @Test
    @Transactional
    public void findClientsByTrainerUserId() {
        List<ClientDto> clients = trainerService.findClientsByTrainerUserId(2L);
        assertEquals(1, clients.size());
        assertEquals(10, clients.get(0).getId());
    }

    @Test
    public void create() {
        User clientUser = userService.findById(4L);
        RegistrationDto dto = new RegistrationDto();
        dto.setGender(Gender.MALE);
        dto.setFirstName("Test");
        dto.setLastName("Testsson");
        dto.setCity("Prague");
        Trainer trainer = trainerService.create(clientUser, dto);

        assertEquals(Gender.MALE, trainer.getGender());
        assertEquals("Test", trainer.getFirstName());
        assertEquals("Testsson", trainer.getLastName());
        assertEquals("Prague", trainer.getCity());
    }

    @Test
    public void update() {
        User clientUser = userService.findById(4L);
        RegistrationDto dto = new RegistrationDto();
        dto.setGender(Gender.MALE);
        dto.setFirstName("Test");
        dto.setLastName("Testsson");
        dto.setCity("Prague");
        Trainer trainer = trainerService.create(clientUser, dto);

        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setFirstName("NewName");
        trainerDto.setLastName("NewLastName");
        trainerDto.setCity("Birmingham");
        trainerDto.setDescription("It's me!");
        trainerDto.setGender(Gender.MALE);

        trainerDto = trainerService.update(trainer.getId(), trainerDto);

        assertEquals(Gender.MALE, trainerDto.getGender());
        assertEquals("NewName", trainerDto.getFirstName());
        assertEquals("NewLastName", trainerDto.getLastName());
        assertEquals("Birmingham", trainerDto.getCity());
        assertEquals("It's me!", trainerDto.getDescription());
    }

    @Test
    public void handleRequest_accept() {
        Mockito.doNothing().when(notificationService).sendNotification(any(), any(), any(), any());

        boolean result = trainerService.handleRequest(2L, 10L, true);
        assertTrue(result);
        Mockito.verify(notificationService, Mockito.times(1)).sendNotification(any(), any(), any(), any());
    }

    @Test
    public void handleRequest_decline() {
        Mockito.doNothing().when(notificationService).sendNotification(any(), any(), any(), any());

        boolean result = trainerService.handleRequest(2L, 10L, false);
        assertTrue(result);
        Mockito.verify(notificationService, Mockito.times(1)).sendNotification(any(), any(), any(), any());
    }

    @Test
    @Transactional
    public void removeClientFromList() {
        boolean result = trainerService.removeClientFromList(10L);
        assertTrue(result);
    }

    @Test
    @Transactional
    public void removeProgramFromList() {
        Mockito.when(authService.getUserId()).thenReturn(2L);
        boolean result = trainerService.removeProgramFromList(10L);
        assertTrue(result);
    }
}