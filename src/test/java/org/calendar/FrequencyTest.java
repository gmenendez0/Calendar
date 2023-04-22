package org.calendar;

import org.calendar.event.frequency.FrequencyAnnual;
import org.calendar.event.frequency.FrequencyDiary;
import org.calendar.event.frequency.FrequencyMonthly;
import org.calendar.event.frequency.FrequencyWeekly;
import org.junit.Test;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class FrequencyTest {

    @Test
    public void diaryWithCommonInterval(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plus(20, ChronoUnit.DAYS);
        int interval = 20;

        var frequency = new FrequencyDiary(interval);

        LocalDateTime laterOfClass = frequency.nextEventDateTime(now);
        assertEquals(later, laterOfClass);
    }

    @Test
    public void diaryWithZeroInterval(){
        LocalDateTime now = LocalDateTime.now();
        int interval = 0;
        var frequency = new FrequencyDiary(interval);
        LocalDateTime laterOfClass = frequency.nextEventDateTime(now);
        assertEquals(laterOfClass, now);
    }

    @Test
    public void annualFrequency(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plus(1, ChronoUnit.YEARS);
        var frequency = new FrequencyAnnual();
        LocalDateTime laterOfClass = frequency.nextEventDateTime(now);
        assertEquals(later, laterOfClass);
    }

    @Test
    public void monthlyFrequency(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plus(1, ChronoUnit.MONTHS);
        var frequency = new FrequencyMonthly();
        LocalDateTime laterOfClass = frequency.nextEventDateTime(now);
        assertEquals(later, laterOfClass);
    }

    @Test
    public void weeklyFrequency1(){
        ArrayList<DayOfWeek> array = new ArrayList<>();
        array.add(DayOfWeek.MONDAY);
        array.add(DayOfWeek.WEDNESDAY);
        array.add(DayOfWeek.FRIDAY);
        LocalDateTime wednesday = LocalDateTime.of(2023, 4, 12, 10, 30);
        LocalDateTime friday = LocalDateTime.of(2023, 4, 14, 10, 30);
        var frequency = new FrequencyWeekly(array);

        LocalDateTime response = frequency.nextEventDateTime(wednesday);

        assertEquals(friday, response);
    }

    @Test
    public void weeklyFrequency2(){
        ArrayList<DayOfWeek> array = new ArrayList<>();
        array.add(DayOfWeek.TUESDAY);
        array.add(DayOfWeek.THURSDAY);
        array.add(DayOfWeek.SATURDAY);
        LocalDateTime tuesday = LocalDateTime.of(2023, 4, 11, 10, 30);
        LocalDateTime thursday = LocalDateTime.of(2023, 4, 13, 10, 30);
        LocalDateTime saturday = LocalDateTime.of(2023, 4, 15, 10, 30);
        var frequency = new FrequencyWeekly(array);

        LocalDateTime response1 = frequency.nextEventDateTime(tuesday);
        LocalDateTime response2 = frequency.nextEventDateTime(response1);

        assertEquals(response1, thursday);
        assertEquals(response2, saturday);
    }

    @Test
    public void weeklyFrequencyWithOneDay(){
        LocalDateTime wednesday1 = LocalDateTime.of(2023, 4, 12, 10, 30);
        LocalDateTime wednesday2 = LocalDateTime.of(2023, 4, 19, 10, 30);
        ArrayList<DayOfWeek> array = new ArrayList<>();
        array.add(DayOfWeek.WEDNESDAY);
        var frequency = new FrequencyWeekly(array);

        LocalDateTime response1 = frequency.nextEventDateTime(LocalDateTime.of(2023, 4, 11, 10, 30));
        assertEquals(wednesday1, response1);
        LocalDateTime response2 = frequency.nextEventDateTime(response1);
        assertEquals(wednesday2, response2);
    }
}