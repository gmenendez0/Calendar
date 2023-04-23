package org.calendar.event;

import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class WholeDayEventTest {

    @Test
    public void oneDayEvent(){
        String title = "Title";
        String description = "Description";
        LocalDate now = LocalDate.now();
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
        LocalDate now = LocalDate.now();
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
        LocalDate now = LocalDate.now();

        WholeDayEvent event = new WholeDayEvent(title, description, now);

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDateTime startTomorrow = tomorrow.atStartOfDay();
        LocalDateTime endTomorrow = tomorrow.plusDays(1).atStartOfDay();

        event.setStartDate(tomorrow);

        assertEquals(startTomorrow, event.getStartDateTime());
        assertEquals(endTomorrow, event.getEndingDateTime());
    }

}