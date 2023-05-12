package org.calendar.event;

import org.calendar.event.frequency.Frequency;
import org.calendar.event.frequency.FrequencyDaily;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class WholeDayEventTest {

    @Test
    public void oneDayEvent(){
        String title = "Title";
        String description = "Description";
        LocalDate now = LocalDate.of(2023,4,30);
        LocalDateTime start = now.atStartOfDay();
        LocalDateTime end = now.plusDays(1).atStartOfDay();

        WholeDayEvent event = new WholeDayEvent(title, description, now);

        assertEquals(start, event.getStartDateTime());
        assertEquals(end, event.getEndingDateTime());
    }

    @Test
    public void isWholeDayEvent(){
        String title = "Title";
        String description = "Description";
        LocalDate now = LocalDate.of(2023,4,30);
        LocalDateTime start = now.atStartOfDay();
        LocalDateTime end = now.plusDays(1).atStartOfDay();

        WholeDayEvent event = new WholeDayEvent(title, description, now);

        assertEquals(event.getStartDateTime(), start);    
        assertEquals(event.getEndingDateTime(), end);
    }

    @Test
    public void changeDayEvent(){
        String title = "Title";
        String description = "Description";
        LocalDate now = LocalDate.of(2023,4,30);

        WholeDayEvent event = new WholeDayEvent(title, description, now);

        LocalDate tomorrow = now.plusDays(1);
        LocalDateTime startTomorrow = tomorrow.atStartOfDay();
        LocalDateTime endTomorrow = tomorrow.atTime(23, 59, 59);

        event.setStartDate(tomorrow);

        assertEquals(startTomorrow, event.getStartDateTime());
        assertEquals(endTomorrow, event.getEndingDateTime());
    }

    @Test
    public void eventInfinity(){
        String title = "Title";
        String description = "Description";
        LocalDate now = LocalDate.of(2023,4,30);
        WholeDayEvent eventInfinity = new WholeDayEvent(title, description, now);

        Frequency frequencyWithoutDeadline = new FrequencyDaily(5, null);

        eventInfinity.setEventFrequency(frequencyWithoutDeadline);

        LocalDateTime day = eventInfinity.getStartDateTime();

        // Como crear un ciclo infinito es inviable, se simula la infinidad con un ciclo muy grande.
        // Este evento se repetira un millon de veces.
        for (int i = 5; i <= 5000000; i += 5){
            LocalDateTime plusDay = day.plusDays(5);
            LocalDateTime nextDayEvent = eventInfinity.getNextEventRegardDateTime(day);
            assertEquals(plusDay, nextDayEvent);
            day = nextDayEvent;
        }
    }

}