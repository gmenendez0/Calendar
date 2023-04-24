package org.calendar.frequency;

import org.calendar.event.frequency.FrequencyWeekly;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FrequencyWeeklyTest {

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

        LocalDateTime response1 = frequency.nextEventDateTime(LocalDateTime.of(2023, 4, 5, 10, 30));
        assertEquals(wednesday1, response1);
        LocalDateTime response2 = frequency.nextEventDateTime(response1);
        assertEquals(wednesday2, response2);
    }
}
