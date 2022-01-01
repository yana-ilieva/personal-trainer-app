package com.fitbook.controller;

import com.fitbook.dto.NutritionPlanDto;
import com.fitbook.service.NutritionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nutrition_plan")
public class NutritionPlanController {

    private final NutritionPlanService nutritionPlanService;

    @Autowired
    public NutritionPlanController(NutritionPlanService nutritionPlanService) {
        this.nutritionPlanService = nutritionPlanService;
    }

    @GetMapping("/{id}")
    public NutritionPlanDto findById(@PathVariable("id") Long id) {
        return nutritionPlanService.findByIdPublic(id);
    }

    @PostMapping
    public NutritionPlanDto create(@RequestBody NutritionPlanDto nutritionPlanDto) {
        return nutritionPlanService.create(nutritionPlanDto);
    }

    @PutMapping("/{id}")
    public NutritionPlanDto update(@PathVariable("id") Long id, @RequestBody NutritionPlanDto nutritionPlanDto) {
        return nutritionPlanService.update(id, nutritionPlanDto);
    }
}
