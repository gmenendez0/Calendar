package org.calendar;

import org.junit.Test;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class FrecuencyTest {

    @Test
    public void diaryWithCommonInterval(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plus(20, ChronoUnit.DAYS);
        int interval = 20;
        var frecuency = new FrecuencyDiary(interval);
        LocalDateTime laterOfClass = frecuency.nextDate(now);
        assertEquals(later, laterOfClass);
    }

    @Test
    public void diaryWithZeroInterval(){
        LocalDateTime now = LocalDateTime.now();
        int interval = 0;
        var frecuency = new FrecuencyDiary(interval);
        LocalDateTime laterOfClass = frecuency.nextDate(now);
        assertEquals(laterOfClass, now);
    }

    @Test
    public void annualFrecuency(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plus(1, ChronoUnit.YEARS);
        var frecuency = new FrecuencyAnnual();
        LocalDateTime laterOfClass = frecuency.nextDate(now);
        assertEquals(later, laterOfClass);
    }

    @Test
    public void monthlyFrecuency(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plus(1, ChronoUnit.MONTHS);
        var frecuency = new FrecuencyMonthly();
        LocalDateTime laterOfClass = frecuency.nextDate(now);
        assertEquals(later, laterOfClass);
    }

    @Test
    public void weeklyFrecuency1(){
        ArrayList<DayOfWeek> array = new ArrayList<DayOfWeek>();
        array.add(DayOfWeek.MONDAY);
        array.add(DayOfWeek.WEDNESDAY);
        array.add(DayOfWeek.FRIDAY);
        LocalDateTime wednesday = LocalDateTime.of(2023, 4, 12, 10, 30);
        LocalDateTime friday = LocalDateTime.of(2023, 4, 14, 10, 30);
        var frecuency = new FrecuencyWeekly(array);

        LocalDateTime response = frecuency.nextDate(wednesday);

        assertEquals(friday, response);
    }

    @Test
    public void weeklyFrecuency2(){
        ArrayList<DayOfWeek> array = new ArrayList<DayOfWeek>();
        array.add(DayOfWeek.TUESDAY);
        array.add(DayOfWeek.THURSDAY);
        array.add(DayOfWeek.SATURDAY);
        LocalDateTime tuesday = LocalDateTime.of(2023, 4, 11, 10, 30);
        LocalDateTime thursday = LocalDateTime.of(2023, 4, 13, 10, 30);
        LocalDateTime saturday = LocalDateTime.of(2023, 4, 15, 10, 30);
        var frecuency = new FrecuencyWeekly(array);

        LocalDateTime response1 = frecuency.nextDate(tuesday);
        LocalDateTime response2 = frecuency.nextDate(response1);

        assertEquals(response1, thursday);
        assertEquals(response2, saturday);
    }

    @Test
    public void weeklyFrecuencyWithOneDay(){
        LocalDateTime wednesday1 = LocalDateTime.of(2023, 4, 12, 10, 30);
        LocalDateTime wednesday2 = LocalDateTime.of(2023, 4, 19, 10, 30);
        ArrayList<DayOfWeek> array = new ArrayList<DayOfWeek>();
        array.add(DayOfWeek.WEDNESDAY);
        var frecuency = new FrecuencyWeekly(array);

        LocalDateTime response1 = frecuency.nextDate(LocalDateTime.of(2023, 4, 11, 10, 30));
        assertEquals(wednesday1, response1);
        LocalDateTime response2 = frecuency.nextDate(response1);
        assertEquals(wednesday2, response2);
    }
}