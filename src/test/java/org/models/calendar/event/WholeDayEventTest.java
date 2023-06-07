package org.models.calendar.event;

import org.models.calendar.event.frequency.Frequency;
import org.models.calendar.event.frequency.FrequencyDaily;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class WholeDayEventTest {
    private WholeDayEvent wholeDayEvent;
    private LocalDate dateEvent;

    @Before
    public void initialize(){
        String title = "Title";
        String description = "Description";
        setDate();
        wholeDayEvent = new WholeDayEvent(title, description, dateEvent);
        Frequency frequencyWithoutDeadline = new FrequencyDaily(5, null);
        wholeDayEvent.setFrequency(frequencyWithoutDeadline);
    }

    private void setDate(){
        this.dateEvent = LocalDate.of(2023,4,30);
    }

    @Test
    public void changeDayEvent(){
        LocalDate anyDate = LocalDate.of(2023, 5, 14);
        LocalDateTime anyDateStart = anyDate.atStartOfDay();
        LocalDateTime anyDateEnd = anyDate.atTime(23,59,59);

        wholeDayEvent.setStartDate(anyDate);

        assertEquals(anyDateStart, wholeDayEvent.getStartDateTime());
        assertEquals(anyDateEnd, wholeDayEvent.getEndingDateTime());

        setDate();
    }

    @Test
    public void eventInfinity(){
        LocalDateTime day = wholeDayEvent.getStartDateTime();
        for (int i = 5; i <= 100000; i += 5){
            LocalDateTime plusDay = day.plusDays(5);
            LocalDateTime nextDayEvent = wholeDayEvent.getNextRepetitionStartDateTime(day);
            assertEquals(plusDay, nextDayEvent);
            day = nextDayEvent;
        }
    }
}