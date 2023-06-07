package org.models.calendar.frequency;

import org.models.calendar.event.frequency.FrequencyAnnual;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class FrequencyAnnualTest {
    @Test
    public void annualFrequency(){
        LocalDateTime now = LocalDateTime.of(2023, 4, 30, 12, 30);
        LocalDateTime later = now.plus(1, ChronoUnit.YEARS);
        var frequency = new FrequencyAnnual(null);
        LocalDateTime laterOfClass = frequency.nextRepetitionDateTime(now);
        assertEquals(later, laterOfClass);
    }
}
