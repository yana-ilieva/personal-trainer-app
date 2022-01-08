package com.fitbook.util;

import com.fitbook.dto.*;
import com.fitbook.entity.chat.Chat;
import com.fitbook.entity.chat.Message;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.client.Progress;
import com.fitbook.entity.notification.Notification;
import com.fitbook.entity.program.*;
import com.fitbook.entity.trainer.Trainer;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
        trainerDto.setDescription(trainer.getDescription());
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
        clientDto.setId(client.getId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setBirthDate(client.getBirthDate());
        clientDto.setGender(client.getGender());
        clientDto.setHeight(client.getHeight());
        clientDto.setDescription(client.getDescription());
        return clientDto;
    }

    public Program map(ProgramDto programDto) {
        Program program = new Program();
        program.setDescription(programDto.getDescription());
        program.setName(programDto.getName());
        if (programDto.getProgramParts() != null) {
            program.setProgramParts(programDto.getProgramParts().stream().map(this::map).collect(Collectors.toList()));
        }
        return program;
    }

    private ProgramPart map(ProgramPartDto programPartDto) {
        ProgramPart programPart = new ProgramPart();
        programPart.setId(programPartDto.getId());
        programPart.setWeekDay(programPartDto.getWeekDay());
        programPart.setRestBetweenExercises(programPartDto.getRestBetweenExercises());
        if (programPartDto.getExerciseUnits() != null) {
            programPart.setExerciseUnits(programPartDto.getExerciseUnits().stream().map(this::map).collect(Collectors.toList()));
        }
        return programPart;
    }

    private ExerciseUnit map(ExerciseUnitDto exerciseUnitDto) {
        ExerciseUnit exerciseUnit = new ExerciseUnit();
        exerciseUnit.setId(exerciseUnitDto.getId());
        exerciseUnit.setRepetitions(exerciseUnitDto.getRepetitions());
        if (exerciseUnitDto.getExercise() != null) {
            exerciseUnit.setExercise(map(exerciseUnitDto.getExercise()));
        }
        return exerciseUnit;
    }

    private Exercise map(ExerciseDto exerciseDto) {
        Exercise exercise = new Exercise();
        exercise.setId(exerciseDto.getId());
        exercise.setName(exerciseDto.getName());
        return exercise;
    }

    public ProgramDto map(Program program) {
        ProgramDto programDto = new ProgramDto();
        programDto.setId(program.getId());
        programDto.setDescription(program.getDescription());
        programDto.setName(program.getName());
        if (program.getProgramParts() != null) {
            programDto.setProgramParts(program.getProgramParts().stream().map(this::map).collect(Collectors.toList()));
        }
        return programDto;
    }

    private ProgramPartDto map(ProgramPart programPart) {
        ProgramPartDto programPartDto = new ProgramPartDto();
        programPartDto.setId(programPart.getId());
        programPartDto.setWeekDay(programPart.getWeekDay());
        programPartDto.setRestBetweenExercises(programPart.getRestBetweenExercises());
        if (programPart.getExerciseUnits() != null) {
            programPartDto.setExerciseUnits(programPart.getExerciseUnits().stream().map(this::map).collect(Collectors.toList()));
        }
        return programPartDto;
    }

    private ExerciseUnitDto map(ExerciseUnit exerciseUnit) {
        ExerciseUnitDto exerciseUnitDto = new ExerciseUnitDto();
        exerciseUnitDto.setId(exerciseUnit.getId());
        exerciseUnitDto.setRepetitions(exerciseUnit.getRepetitions());
        if (exerciseUnit.getExercise() != null) {
            exerciseUnitDto.setExercise(map(exerciseUnit.getExercise()));
        }
        return exerciseUnitDto;
    }

    public ExerciseDto map(Exercise exercise) {
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setId(exercise.getId());
        exerciseDto.setName(exercise.getName());
        return exerciseDto;
    }

    public ProgressDto map(Progress progress) {
        ProgressDto progressDto = new ProgressDto();
        progressDto.setId(progress.getId());
        progressDto.setClientId(progress.getClient().getId());
        progressDto.setCreatedTimestamp(progress.getCreatedTimestamp());
        progressDto.setWeight(progress.getWeight());
        progressDto.setChestMeasurement(progress.getChestMeasurement());
        progressDto.setHipMeasurement(progress.getHipMeasurement());
        progressDto.setThighMeasurement(progress.getThighMeasurement());
        progressDto.setWaistMeasurement(progress.getWaistMeasurement());
        progressDto.setUpperArmMeasurement(progress.getUpperArmMeasurement());
        return progressDto;
    }

    public Progress map(ProgressDto progressDto) {
        Progress progress = new Progress();
        progress.setId(progressDto.getId());
        progress.setCreatedTimestamp(progressDto.getCreatedTimestamp());
        progress.setWeight(progressDto.getWeight());
        progress.setChestMeasurement(progressDto.getChestMeasurement());
        progress.setHipMeasurement(progressDto.getHipMeasurement());
        progress.setThighMeasurement(progressDto.getThighMeasurement());
        progress.setWaistMeasurement(progressDto.getWaistMeasurement());
        progress.setUpperArmMeasurement(progressDto.getUpperArmMeasurement());
        return progress;
    }

    public void map(ProgressDto progressDto, Progress progress) {
        progress.setWeight(progressDto.getWeight());
        progress.setChestMeasurement(progressDto.getChestMeasurement());
        progress.setHipMeasurement(progressDto.getHipMeasurement());
        progress.setThighMeasurement(progressDto.getThighMeasurement());
        progress.setWaistMeasurement(progressDto.getWaistMeasurement());
        progress.setUpperArmMeasurement(progressDto.getUpperArmMeasurement());
    }

    public void map(ProgramDto programDto, Program program) {
        program.setId(programDto.getId());
        program.setDescription(programDto.getDescription());
        program.setName(programDto.getName());
        if (programDto.getProgramParts() != null) {
            program.setProgramParts(programDto.getProgramParts().stream().map(this::map).collect(Collectors.toList()));
        }
    }

    public NutritionPlan map(NutritionPlanDto nutritionPlanDto) {
        NutritionPlan nutritionPlan = new NutritionPlan();
        nutritionPlan.setName(nutritionPlanDto.getName());
        nutritionPlan.setDescription(nutritionPlanDto.getDescription());
        nutritionPlan.setNutritionPlanParts(nutritionPlanDto.getNutritionPlanPartDtos().stream().map(this::map).collect(Collectors.toList()));
        return nutritionPlan;
    }

    private NutritionPlanPart map(NutritionPlanPartDto nutritionPlanPartDto) {
        NutritionPlanPart nutritionPlanPart = new NutritionPlanPart();
        nutritionPlanPart.setId(nutritionPlanPartDto.getId());
        nutritionPlanPart.setWeekDay(nutritionPlanPartDto.getWeekDay());
        nutritionPlanPart.setContent(nutritionPlanPartDto.getContent());
        return nutritionPlanPart;
    }

    public NutritionPlanDto map(NutritionPlan nutritionPlan) {
        NutritionPlanDto nutritionPlanDto = new NutritionPlanDto();
        nutritionPlanDto.setId(nutritionPlan.getId());
        nutritionPlanDto.setName(nutritionPlan.getName());
        nutritionPlanDto.setDescription(nutritionPlan.getDescription());
        nutritionPlanDto.setNutritionPlanPartDtos(nutritionPlan.getNutritionPlanParts().stream().map(this::map).collect(Collectors.toList()));
        return nutritionPlanDto;
    }

    private NutritionPlanPartDto map(NutritionPlanPart nutritionPlanPart) {
        NutritionPlanPartDto nutritionPlanPartDto = new NutritionPlanPartDto();
        nutritionPlanPartDto.setId(nutritionPlanPart.getId());
        nutritionPlanPartDto.setWeekDay(nutritionPlanPart.getWeekDay());
        nutritionPlanPartDto.setContent(nutritionPlanPart.getContent());
        return nutritionPlanPartDto;
    }

    public void map(NutritionPlanDto nutritionPlanDto, NutritionPlan nutritionPlan) {
        nutritionPlan.setId(nutritionPlanDto.getId());
        nutritionPlan.setName(nutritionPlanDto.getName());
        nutritionPlan.setDescription(nutritionPlanDto.getDescription());
        nutritionPlan.setNutritionPlanParts(nutritionPlanDto.getNutritionPlanPartDtos().stream().map(this::map).collect(Collectors.toList()));
    }

    public void map(NutritionPlanPartDto nutritionPlanPartDto, NutritionPlanPart nutritionPlanPart) {
        nutritionPlanPart.setId(nutritionPlanPartDto.getId());
        nutritionPlanPart.setWeekDay(nutritionPlanPartDto.getWeekDay());
        nutritionPlanPart.setContent(nutritionPlanPartDto.getContent());
    }

    public Chat map(ChatDto chatDto) {
        Chat chat = new Chat();
        chat.setId(chatDto.getId());
        return chat;
    }

    public ChatDto map(Chat chat) {
        ChatDto chatDto = new ChatDto();
        chatDto.setId(chat.getId());
        chatDto.setClient(mapShort(chat.getClient()));
        chatDto.setTrainer(mapShort(chat.getTrainer()));
        return chatDto;
    }

    public ClientShortDto mapShort(Client client) {
        ClientShortDto clientShortDto = new ClientShortDto();
        clientShortDto.setId(client.getId());
        clientShortDto.setName(client.getFirstName() + " " + client.getLastName());
        return clientShortDto;
    }

    public TrainerShortDto mapShort(Trainer trainer) {
        TrainerShortDto trainerShortDto = new TrainerShortDto();
        trainerShortDto.setId(trainer.getId());
        trainerShortDto.setName(trainer.getFirstName() + " " + trainer.getLastName());
        return trainerShortDto;
    }

    public MessageDto map(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setContent(message.getContent());
        messageDto.setSender(message.getSender());
        messageDto.setReceiver(message.getReceiver());
        messageDto.setCreatedTime(message.getCreatedTime());
        messageDto.setChatId(message.getChat().getId());
        return messageDto;
    }

    public Message map(MessageDto messageDto) {
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setSender(messageDto.getSender());
        message.setReceiver(messageDto.getReceiver());
        return message;
    }

    public NotificationDto map(Notification notification) {
        NotificationDto dto = new NotificationDto();
        dto.setNotificationType(notification.getNotificationType());
        dto.setCreatedTimeStamp(notification.getCreatedTimestamp());
        dto.setTrainerId(notification.getTrainer().getId());
        dto.setClientId(notification.getClient().getId());
        dto.setTrainerName(notification.getTrainer().getFirstName() + " " + notification.getTrainer().getLastName());
        dto.setClientName(notification.getClient().getFirstName() + " " + notification.getClient().getLastName());
        return dto;
    }
}
