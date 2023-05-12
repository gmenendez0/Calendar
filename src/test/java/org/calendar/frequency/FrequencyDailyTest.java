package org.calendar.frequency;

import org.calendar.event.frequency.FrequencyDaily;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class FrequencyDailyTest {
    @Test
    public void dailyWithCommonInterval(){
        LocalDateTime now = LocalDateTime.of(2023, 4, 30, 12, 30);
        LocalDateTime later = now.plus(20, ChronoUnit.DAYS);
        int interval = 20;

        var frequency = new FrequencyDaily(interval, null);

        LocalDateTime laterOfClass = frequency.nextEventDateTime(now);
        assertEquals(later, laterOfClass);
    }

    @Test
    public void dailyWithZeroInterval(){
        LocalDateTime now = LocalDateTime.of(2023, 4, 20, 12, 30);
        int interval = 0;
        var frequency = new FrequencyDaily(interval, null);
        LocalDateTime laterOfClass = frequency.nextEventDateTime(now);
        assertEquals(laterOfClass, now);
    }

    @Test
    public void checkReturnLastDateTime(){
        LocalDate date = LocalDate.of(2023, 4, 20);
        LocalTime time = LocalTime.of(12,30,0);
        LocalDateTime dateTime = LocalDateTime.of(2023, 4, 20,12,30,0);
        int interval = 10;
        var frequency = new FrequencyDaily(interval, date);
        LocalDateTime response = frequency.getDeadlineDateTime(time);

        assertEquals(response, dateTime);
    }

    @Test
    public void verifyIfHasNext(){
        LocalDate date = LocalDate.of(2023, 4, 20);
        int interval = 1;
        var frequency = new FrequencyDaily(interval, date);
        LocalDateTime dateTest1 = LocalDateTime.of(2023, 4, 18,12,0,0);
        LocalDateTime dateTest2 = LocalDateTime.of(2023, 4, 19,12,0,0);
        LocalDateTime dateTest3 = LocalDateTime.of(2023, 4, 20,12,0,0);

        assertTrue(frequency.hasNextDate(dateTest1));
        assertTrue(frequency.hasNextDate(dateTest2));
        assertFalse(frequency.hasNextDate(dateTest3));
    }
}
