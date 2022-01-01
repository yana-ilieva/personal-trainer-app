package com.fitbook.entity.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nutrition_plans")
@Entity
public class NutritionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "nutritionPlan", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<NutritionPlanPart> nutritionPlanParts;

    public NutritionPlan(NutritionPlan nutritionPlan) {
        this.id = null;
        this.name = nutritionPlan.name;
        this.description = nutritionPlan.description;
        this.nutritionPlanParts = nutritionPlan.nutritionPlanParts;
    }
}
