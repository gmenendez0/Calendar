package org.calendar.frequency;

import org.calendar.event.frequency.FrequencyAnnual;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class FrequencyAnnualTest {
    @Test
    public void annualFrequency(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plus(1, ChronoUnit.YEARS);
        var frequency = new FrequencyAnnual();
        LocalDateTime laterOfClass = frequency.nextEventDateTime(now);
        assertEquals(later, laterOfClass);
    }
}
