package com.fitbook.util;

import com.fitbook.entity.program.NutritionPlanPart;
import com.fitbook.enums.WeekDay;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.fitbook.enums.WeekDay.*;

@Component
public class NutritionPlanPartUtil {

    public List<NutritionPlanPart> transformNutritionPlanParts(List<NutritionPlanPart> nutritionPlanParts) {
        if (nutritionPlanParts.size() > 7) {
            throw new RuntimeException("A nutrition plan cannot have more than seven parts");
        }

        nutritionPlanParts = nutritionPlanParts.stream().sorted(Comparator.comparing(NutritionPlanPart::getWeekDay)).collect(Collectors.toList());

        NutritionPlanPart[] nutritionPlanPartsArr = new NutritionPlanPart[7];

        nutritionPlanParts.forEach(nutritionPlanPart -> {
            switch (nutritionPlanPart.getWeekDay()) {
                case MONDAY: nutritionPlanPartsArr[0] = nutritionPlanPart; break;
                case TUESDAY: nutritionPlanPartsArr[1] = nutritionPlanPart; break;
                case WEDNESDAY: nutritionPlanPartsArr[2] = nutritionPlanPart; break;
                case THURSDAY: nutritionPlanPartsArr[3] = nutritionPlanPart; break;
                case FRIDAY: nutritionPlanPartsArr[4] = nutritionPlanPart; break;
                case SATURDAY: nutritionPlanPartsArr[5] = nutritionPlanPart; break;
                case SUNDAY: nutritionPlanPartsArr[6] = nutritionPlanPart; break;
            }
        });

        for (int i = 0; i < nutritionPlanPartsArr.length; i++) {
            if (nutritionPlanPartsArr[i] == null) {
                nutritionPlanPartsArr[i] = nutritionPlanPart(weekDay(i));
            }
        }

        return Arrays.asList(nutritionPlanPartsArr);
    }

    private NutritionPlanPart nutritionPlanPart(WeekDay weekDay) {
        NutritionPlanPart nutritionPlanPart = new NutritionPlanPart();
        nutritionPlanPart.setWeekDay(weekDay);
        return nutritionPlanPart;
    }

    private WeekDay weekDay(int i) {
        switch(i) {
            case 0: return MONDAY;
            case 1: return WeekDay.TUESDAY;
            case 2: return WeekDay.WEDNESDAY;
            case 3: return WeekDay.THURSDAY;
            case 4: return WeekDay.FRIDAY;
            case 5: return WeekDay.SATURDAY;
            case 6: return WeekDay.SUNDAY;
            default: throw new RuntimeException("Invalid number of week day");
        }
    }
}
