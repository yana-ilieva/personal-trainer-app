package com.fitbook.service;

import com.fitbook.dto.ExerciseDto;
import com.fitbook.repository.ExerciseRepository;
import com.fitbook.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final Mapper mapper;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository, Mapper mapper) {
        this.exerciseRepository = exerciseRepository;
        this.mapper = mapper;
    }

    public List<ExerciseDto> getAll() {
        return exerciseRepository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }
}
