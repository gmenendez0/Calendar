package org.calendar;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class wholeDayEventTest {

    @Test
    public void oneDayEvent(){
        String title = "Title";
        String description = "Description";
        LocalDate now = LocalDate.now();
        LocalDateTime start = now.atStartOfDay();
        LocalDateTime end = now.atTime(23,59,59);

        wholeDayEvent event = new wholeDayEvent(1, title, description, now);

        assertEquals(start, event.whenTheEventStart());
        assertEquals(end, event.whenTheEventEnd());
    }

    @Test
    public void isWholeDayEvent(){
        String title = "Title";
        String description = "Description";
        LocalDate now = LocalDate.now();

        wholeDayEvent event = new wholeDayEvent(2, title, description, now);

        assertTrue(event.isWholeDay());
    }

    @Test
    public void changeDayEvent(){
        String title = "Title";
        String description = "Description";
        LocalDate now = LocalDate.now();
        LocalDateTime start = now.atStartOfDay();
        LocalDateTime end = now.atTime(23,59,59);

        wholeDayEvent event = new wholeDayEvent(1, title, description, now);

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDateTime startTomorrow = tomorrow.atStartOfDay();
        LocalDateTime endTomorrow = tomorrow.atTime(23,59,59);

        event.setStartDate(tomorrow);

        assertEquals(tomorrow, event.getStartDate());
    }
}