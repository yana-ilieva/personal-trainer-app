package com.fitbook.repository;

import com.fitbook.entity.program.NutritionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionPlanRepository extends JpaRepository<NutritionPlan, Long> {
}
