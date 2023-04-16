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
        Event newEvent = new Event(1, title, description, LocalDate.now().atStartOfDay(), LocalDate.now().atTime(12,0,0));

        int idResponse = newEvent.getId();
        String titleResponse = newEvent.getTitle();
        String descriptionResponse = newEvent.getDescription();

        assertEquals(idResponse, 1);
        assertEquals(titleResponse, title);
        assertEquals(descriptionResponse, description);
    }

//    public void verifyDurationWholeDay(){
//        String title = "title of event";
//        String description = "description of event";
//        LocalDate now = LocalDate.now();
//        LocalDateTime start = now.atTime(12,0,0);
//        LocalDateTime end = now.atTime(18,0,0);
//        Event newEvent = new Event(2, title, description, start, end);
//
//        LocalDateTime startResponse = newEvent.whenTheEventStart();
//        LocalDateTime endResponse = newEvent.whenTheEventEnd();
//
//        assertEquals(startResponse, start);
//        assertEquals(endResponse, end);
//    }

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

    @Test
    public void verifyChangeDuration(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start1 = LocalDateTime.now().withHour(12).withMinute(00).withSecond(00);
        LocalDateTime end1 = LocalDateTime.now().plusDays(2).withHour(12).withMinute(00).withSecond(00);
        LocalDateTime start2 = LocalDateTime.now().withHour(00).withMinute(00).withSecond(00);
        LocalDateTime end2 = LocalDateTime.now().plusDays(2).withHour(18).withMinute(00).withSecond(00);

        Event newEvent = new Event(3, title, description, start1, end1);
        newEvent.changeEventStart(start2);
        newEvent.changeEventEnd(end2);
        LocalDateTime startResponse = newEvent.whenTheEventStart();
        LocalDateTime endResponse = newEvent.whenTheEventEnd();

        assertEquals(startResponse, start2);
        assertEquals(endResponse, end2);
    }

    @Test
    public void eventDontHaveReplay(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.now().withHour(12).withMinute(00).withSecond(00);
        LocalDateTime end = LocalDateTime.now().plusDays(2).withHour(12).withMinute(00).withSecond(00);
        Event eventWithoutFrecuency = new Event(4, title, description, start, end);
        assertFalse(eventWithoutFrecuency.eventIsRepeated());
    }

    @Test
    public void eventHaveReplay(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.now().withHour(12).withMinute(00).withSecond(00);
        LocalDateTime end = LocalDateTime.now().plusDays(2).withHour(12).withMinute(00).withSecond(00);
        Event eventWithFrecuency = new Event(4, title, description, start, end);
        eventWithFrecuency.repeatEventDiary(2);
        assertTrue(eventWithFrecuency.eventIsRepeated());
    }

    @Test
    public void eventNoRepeat(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.now().withHour(12).withMinute(00).withSecond(00);
        LocalDateTime end = LocalDateTime.now().plusDays(2).withHour(12).withMinute(00).withSecond(00);
        Event eventWithFrecuency = new Event(4, title, description, start, end);
        eventWithFrecuency.repeatEventDiary(2);
        eventWithFrecuency.noRepeat();
        assertFalse(eventWithFrecuency.eventIsRepeated());
    }

    @Test
    public void nextEventTest(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023,04,16, 12,0);
        LocalDateTime end = start.plusDays(1);
        Event event = new Event(4, title, description, start, end);
        event.repeatEventDiary(1);
        event.addDeadline(LocalDate.of(2023,04,20));

        LocalDateTime day1 = event.whenTheEventStart();
        LocalDateTime day2 = event.nextEventDate(day1);
        assertEquals(day1.plusDays(1), day2);
        LocalDateTime day3 = event.nextEventDate(day2);
        assertEquals(day1.plusDays(2), day3);
        LocalDateTime day4 = event.nextEventDate(day3);
        assertEquals(day1.plusDays(3), day4);
        LocalDateTime day5 = event.nextEventDate(day4);
        assertEquals(day1.plusDays(4), day5);
        LocalDateTime day6 = event.nextEventDate(day5);
        assertNull(day6);
    }
}