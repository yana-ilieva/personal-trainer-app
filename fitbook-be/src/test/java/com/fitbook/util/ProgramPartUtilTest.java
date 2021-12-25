package com.fitbook.util;

import com.fitbook.entity.program.ProgramPart;
import com.fitbook.enums.WeekDay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramPartUtilTest {

    private ProgramPartUtil programPartUtil;

    @BeforeEach
    public void init() {
        programPartUtil = new ProgramPartUtil();
    }

    @Test
    public void transformProgramParts() {
        List<ProgramPart> programParts = List.of(
                programPart(WeekDay.MONDAY),
                programPart(WeekDay.WEDNESDAY),
                programPart(WeekDay.FRIDAY));

        List<ProgramPart> transformedList = programPartUtil.transformProgramParts(programParts);

        assertAll(
                () -> assertEquals(7, transformedList.size()),
                () -> assertEquals(WeekDay.MONDAY, transformedList.get(0).getWeekDay()),
                () -> assertEquals(WeekDay.TUESDAY, transformedList.get(1).getWeekDay()),
                () -> assertEquals(WeekDay.WEDNESDAY, transformedList.get(2).getWeekDay()),
                () -> assertEquals(WeekDay.THURSDAY, transformedList.get(3).getWeekDay()),
                () -> assertEquals(WeekDay.FRIDAY, transformedList.get(4).getWeekDay()),
                () -> assertEquals(WeekDay.SATURDAY, transformedList.get(5).getWeekDay()),
                () -> assertEquals(WeekDay.SUNDAY, transformedList.get(6).getWeekDay())
        );
    }

    private ProgramPart programPart(WeekDay weekDay) {
        return ProgramPart.builder().weekDay(weekDay).build();
    }
}