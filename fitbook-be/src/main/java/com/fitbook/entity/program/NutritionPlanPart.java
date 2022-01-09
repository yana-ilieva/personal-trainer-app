package com.fitbook.entity.program;

import com.fitbook.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "nutrition_plan_parts")
@Entity
public class NutritionPlanPart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private WeekDay weekDay;

    private String content;

    @ManyToOne
    @JoinColumn(name = "nutrition_plan_id")
    private NutritionPlan nutritionPlan;
}
