package com.fitbook.service;

import com.fitbook.dto.NutritionPlanDto;
import com.fitbook.entity.program.NutritionPlan;
import com.fitbook.entity.program.NutritionPlanPart;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.NutritionPlanRepository;
import com.fitbook.util.Mapper;
import com.fitbook.util.NutritionPlanPartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutritionPlanService {

    private final NutritionPlanRepository nutritionPlanRepository;

    private final Mapper mapper;

    private final NutritionPlanPartUtil nutritionPlanPartUtil;

    @Autowired
    public NutritionPlanService(NutritionPlanRepository nutritionPlanRepository, Mapper mapper, NutritionPlanPartUtil nutritionPlanPartUtil) {
        this.nutritionPlanRepository = nutritionPlanRepository;
        this.mapper = mapper;
        this.nutritionPlanPartUtil = nutritionPlanPartUtil;
    }

    public NutritionPlanDto findByIdPublic(Long id) {
        return mapper.map(findById(id));
    }

    public NutritionPlan findById(Long id) {
        Optional<NutritionPlan> nutritionPlanOpt = nutritionPlanRepository.findById(id);
        if (nutritionPlanOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Nutrition plan with id %d not found", id));
        }
        return nutritionPlanOpt.get();
    }

    public NutritionPlanDto create(NutritionPlanDto nutritionPlanDto) {
        NutritionPlan nutritionPlan = mapper.map(nutritionPlanDto);

        List<NutritionPlanPart> nutritionPlanPartList = nutritionPlan.getNutritionPlanParts();

        nutritionPlan.setNutritionPlanParts(nutritionPlanPartUtil.transformNutritionPlanParts(nutritionPlanPartList));
        return mapper.map(nutritionPlanRepository.save(nutritionPlan));
    }

    public NutritionPlan create(NutritionPlan nutritionPlan) {
        return nutritionPlanRepository.save(nutritionPlan);
    }

    public NutritionPlanDto update(Long id, NutritionPlanDto nutritionPlanDto) {
        Optional<NutritionPlan> nutritionPlanOpt = nutritionPlanRepository.findById(id);
        if (nutritionPlanOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Nutrition plan with id %d not found", id));
        }

        NutritionPlan nutritionPlan = nutritionPlanOpt.get();
        mapper.map(nutritionPlanDto, nutritionPlan);

        return mapper.map(nutritionPlanRepository.save(nutritionPlan));
    }
}
