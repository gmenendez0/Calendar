package org.calendar.frequency;

import org.calendar.event.frequency.FrequencyMonthly;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class FrequencyMonthlyTest {
    @Test
    public void monthlyFrequency(){
        LocalDateTime now = LocalDateTime.of(2023, 04, 30, 12, 30);
        LocalDateTime later = now.plus(1, ChronoUnit.MONTHS);
        var frequency = new FrequencyMonthly(null);
        LocalDateTime laterOfClass = frequency.nextEventDateTime(now);
        assertEquals(later, laterOfClass);
    }
}
