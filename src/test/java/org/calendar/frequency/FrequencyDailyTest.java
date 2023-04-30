package org.calendar.frequency;

import org.calendar.event.frequency.FrequencyDaily;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class FrequencyDailyTest {
    @Test
    public void diaryWithCommonInterval(){
        LocalDateTime now = LocalDateTime.of(2023, 04, 30, 12, 30);
        LocalDateTime later = now.plus(20, ChronoUnit.DAYS);
        int interval = 20;

        var frequency = new FrequencyDaily(interval);

        LocalDateTime laterOfClass = frequency.nextEventDateTime(now);
        assertEquals(later, laterOfClass);
    }

    @Test
    public void diaryWithZeroInterval(){
        LocalDateTime now = LocalDateTime.of(2023, 04, 30, 12, 30);
        int interval = 0;
        var frequency = new FrequencyDaily(interval);
        LocalDateTime laterOfClass = frequency.nextEventDateTime(now);
        assertEquals(laterOfClass, now);
    }
}
