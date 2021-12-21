package com.fitbook.util;

import com.fitbook.dto.ClientDto;
import com.fitbook.dto.TrainerDto;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.trainer.Trainer;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public TrainerDto map(Trainer trainer) {
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setId(trainer.getId());
        trainerDto.setFirstName(trainer.getFirstName());
        trainerDto.setLastName(trainer.getLastName());
        trainerDto.setBirthDate(trainer.getBirthDate());
        trainerDto.setGender(trainer.getGender());
        trainerDto.setCity(trainer.getCity());
        trainerDto.setNeighborhood(trainer.getNeighborhood());
        trainerDto.setDescription(trainerDto.getDescription());
        return trainerDto;
    }

    public void map(Trainer trainer, TrainerDto trainerDto) {
        trainer.setFirstName(trainerDto.getFirstName());
        trainer.setLastName(trainerDto.getLastName());
        trainer.setBirthDate(trainerDto.getBirthDate());
        trainer.setGender(trainerDto.getGender());
        trainer.setCity(trainerDto.getCity());
        trainer.setNeighborhood(trainerDto.getNeighborhood());
        trainer.setDescription(trainerDto.getDescription());
    }

    public void map(Client client, ClientDto clientDto) {
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setBirthDate(clientDto.getBirthDate());
        client.setGender(clientDto.getGender());
        client.setHeight(clientDto.getHeight());
        client.setDescription(clientDto.getDescription());
    }

    public ClientDto map(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(clientDto.getId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setBirthDate(client.getBirthDate());
        clientDto.setGender(client.getGender());
        clientDto.setHeight(client.getHeight());
        clientDto.setDescription(client.getDescription());
        return clientDto;
    }
}
