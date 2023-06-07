package org.models.calendar.frequency;

import org.models.calendar.event.frequency.FrequencyMonthly;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class FrequencyMonthlyTest {
    @Test
    public void monthlyFrequency(){
        LocalDateTime now = LocalDateTime.of(2023, 4, 30, 12, 30);
        LocalDateTime later = now.plus(1, ChronoUnit.MONTHS);
        var frequency = new FrequencyMonthly(LocalDate.of(2023, 7, 30));
        LocalDateTime laterOfClass = frequency.nextRepetitionDateTime(now);
        assertEquals(later, laterOfClass);
    }
}
