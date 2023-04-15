package org.calendar;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EventTest {

    @Test
    public void viewDataOfEvent(){
        String title = "title of event";
        String description = "description of event";
        Event newEvent = new Event(1, title, description, LocalDate.now());

        int idResponse = newEvent.getId();
        String titleResponse = newEvent.getTitle();
        String descriptionResponse = newEvent.getDescription();

        assertEquals(idResponse, 1);
        assertEquals(titleResponse, title);
        assertEquals(descriptionResponse, description);
    }

    public void verifyDurationWholeDay(){
        String title = "title of event";
        String description = "description of event";
        LocalDate now = LocalDate.now();
        Event newEvent = new Event(2, title, description, now);

        LocalDateTime start = now.atTime(0,0,0);
        LocalDateTime end = now.atTime(23,59,59);

        LocalDateTime startResponse = newEvent.whenTheEventStart();
        LocalDateTime endResponse = newEvent.whenTheEventEnd();

        assertEquals(startResponse, start);
        assertEquals(endResponse, end);
    }

    @Test
    public void verifyDuration(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.now().withHour(12).withMinute(00).withSecond(00);
        LocalDateTime end = LocalDateTime.now().plusDays(2).withHour(12).withMinute(00).withSecond(00);
        Event newEvent = new Event(3, title, description, start, end);

        LocalDateTime startResponse = newEvent.whenTheEventStart();
        LocalDateTime endResponse = newEvent.whenTheEventEnd();

        assertEquals(startResponse, start);
        assertEquals(endResponse, end);
    }

}