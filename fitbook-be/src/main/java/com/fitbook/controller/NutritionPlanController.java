package com.fitbook.controller;

import com.fitbook.dto.NutritionPlanDto;
import com.fitbook.service.NutritionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nutrition_plan")
@CrossOrigin(origins = "http://localhost:8080")
public class NutritionPlanController {

    private final NutritionPlanService nutritionPlanService;

    @Autowired
    public NutritionPlanController(NutritionPlanService nutritionPlanService) {
        this.nutritionPlanService = nutritionPlanService;
    }

    @GetMapping("/{id}")
    @Secured("ROLE_TRAINER")
    public NutritionPlanDto findById(@PathVariable("id") Long id) {
        return nutritionPlanService.findByIdPublic(id);
    }

    @PostMapping
    @Secured("ROLE_TRAINER")
    public NutritionPlanDto create(@RequestBody NutritionPlanDto nutritionPlanDto) {
        return nutritionPlanService.create(nutritionPlanDto);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_TRAINER")
    public NutritionPlanDto update(@PathVariable("id") Long id, @RequestBody NutritionPlanDto nutritionPlanDto) {
        return nutritionPlanService.update(id, nutritionPlanDto);
    }
}
