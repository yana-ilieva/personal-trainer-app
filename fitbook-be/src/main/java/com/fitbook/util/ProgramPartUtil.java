package com.fitbook.util;

import com.fitbook.entity.program.ProgramPart;
import com.fitbook.enums.WeekDay;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProgramPartUtil {

    public List<ProgramPart> transformProgramParts(List<ProgramPart> programParts) {
        if (programParts.size() > 7) {
            throw new RuntimeException("A program cannot have more than seven parts");
        }

        programParts = programParts.stream().sorted(Comparator.comparing(ProgramPart::getWeekDay)).collect(Collectors.toList());

        ProgramPart[] programPartsArr = new ProgramPart[7];

        programParts.forEach(programPart -> {
            switch (programPart.getWeekDay()) {
                case MONDAY: programPartsArr[0] = programPart; break;
                case TUESDAY: programPartsArr[1] = programPart; break;
                case WEDNESDAY: programPartsArr[2] = programPart; break;
                case THURSDAY: programPartsArr[3] = programPart; break;
                case FRIDAY: programPartsArr[4] = programPart; break;
                case SATURDAY: programPartsArr[5] = programPart; break;
                case SUNDAY: programPartsArr[6] = programPart; break;
            }
        });

        for (int i = 0; i < programPartsArr.length; i++) {
            if (programPartsArr[i] == null) {
                programPartsArr[i] = programPart(weekDay(i));
            }
        }

        return Arrays.asList(programPartsArr);
    }

    private ProgramPart programPart(WeekDay weekDay) {
        ProgramPart programPart = new ProgramPart();
        programPart.setWeekDay(weekDay);
        return programPart;
    }

    private WeekDay weekDay(int i) {
        switch(i) {
            case 0: return WeekDay.MONDAY;
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
