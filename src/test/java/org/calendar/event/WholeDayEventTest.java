package org.calendar.event;

import org.calendar.event.frequency.Frequency;
import org.calendar.event.frequency.FrequencyDaily;
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
        this.wholeDayEvent = new WholeDayEvent(title, description, dateEvent);
    }

    private void setDate(){
        this.dateEvent = LocalDate.of(2023,4,30);
    }
    @Test
    public void oneDayEvent(){
        LocalDateTime start = dateEvent.atStartOfDay();
        LocalDateTime end = dateEvent.atTime(23,59,59);

        assertEquals(start, wholeDayEvent.getStartDateTime());
        assertEquals(end, wholeDayEvent.getEndingDateTime());
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
        Frequency frequencyWithoutDeadline = new FrequencyDaily(5, null);

        wholeDayEvent.setEventFrequency(frequencyWithoutDeadline);

        LocalDateTime day = wholeDayEvent.getStartDateTime();

        // Como crear un ciclo infinito es inviable, se simula la infinidad con un ciclo muy grande.
        // Este evento se repetira un millon de veces.
        for (int i = 5; i <= 5000000; i += 5){
            LocalDateTime plusDay = day.plusDays(5);
            LocalDateTime nextDayEvent = wholeDayEvent.getNextEventRegardDateTime(day);
            assertEquals(plusDay, nextDayEvent);
            day = nextDayEvent;
        }
    }

}