package com.fitbook.dto;

import com.fitbook.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionPlanPartDto {

    private Long id;

    private WeekDay weekDay;

    private String content;
}
