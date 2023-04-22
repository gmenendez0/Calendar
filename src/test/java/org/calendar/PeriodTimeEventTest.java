package org.calendar;

import org.calendar.event.Event;
import org.calendar.event.PeriodTimeEvent;
import org.calendar.event.frequency.*;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PeriodTimeEventTest {

    //Post: test to check if it returns event data.
    @Test
    public void viewDataAppointmentOfEvent(){
        String title = "title of event";
        String description = "description of event";
        PeriodTimeEvent newEvent = new PeriodTimeEvent(1, title, description, LocalDate.now().atStartOfDay(), LocalDate.now().atTime(12,30,30));

        int idResponse = newEvent.getId();
        String titleResponse = newEvent.getTitle();
        String descriptionResponse = newEvent.getDescription();

        assertEquals(idResponse, 1);
        assertEquals(titleResponse, title);
        assertEquals(descriptionResponse, description);
    }

    //Post: test to check if it returns event duration.
    @Test
    public void verifyDuration(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.now().withHour(12).withMinute(00).withSecond(00);
        LocalDateTime end = LocalDateTime.now().plusDays(2).withHour(12).withMinute(00).withSecond(00);
        PeriodTimeEvent newEvent = new PeriodTimeEvent(3, title, description, start, end);

        LocalDateTime startResponse = newEvent.getStartDateTime();
        LocalDateTime endResponse = newEvent.getEndingDateTime();

        assertEquals(startResponse, start);
        assertEquals(endResponse, end);
    }

    //Post: test to check the change event duration.
    @Test
    public void verifyChangeDuration(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start1 = LocalDateTime.now().withHour(12).withMinute(00).withSecond(00);
        LocalDateTime end1 = LocalDateTime.now().plusDays(2).withHour(12).withMinute(00).withSecond(00);
        LocalDateTime start2 = LocalDateTime.now().withHour(00).withMinute(00).withSecond(00);
        LocalDateTime end2 = LocalDateTime.now().plusDays(2).withHour(18).withMinute(00).withSecond(00);

        PeriodTimeEvent newEvent = new PeriodTimeEvent(3, title, description, start1, end1);
        newEvent.setEventStartDateTime(start2);
        newEvent.setEventEndingDateTime(end2);
        LocalDateTime startResponse = newEvent.getStartDateTime();
        LocalDateTime endResponse = newEvent.getEndingDateTime();

        assertEquals(startResponse, start2);
        assertEquals(endResponse, end2);
    }

    //Post: test to verify if it has no repetition
    @Test
    public void eventDontHaveReplay(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.now().withHour(12).withMinute(00).withSecond(00);
        LocalDateTime end = LocalDateTime.now().plusDays(2).withHour(12).withMinute(00).withSecond(00);
        PeriodTimeEvent eventWithoutFrecuency = new PeriodTimeEvent(4, title, description, start, end);
        assertFalse(eventWithoutFrecuency.IsRepeated());
    }

    //Post: test to verify if it has repetition
    @Test
    public void eventHaveReplay(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.now().withHour(12).withMinute(00).withSecond(00);
        LocalDateTime end = LocalDateTime.now().plusDays(2).withHour(12).withMinute(00).withSecond(00);
        PeriodTimeEvent eventWithFrecuency = new PeriodTimeEvent(4, title, description, start, end);
        Frequency frecuencyEvent = new FrequencyDiary(2);
        eventWithFrecuency.setEventFrequency(frecuencyEvent);
        assertTrue(eventWithFrecuency.IsRepeated());
    }

    //Post: test to verify if canceling repeats works.
    @Test
    public void eventNoRepeat(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.now().withHour(12).withMinute(00).withSecond(00);
        LocalDateTime end = LocalDateTime.now().plusDays(2).withHour(12).withMinute(00).withSecond(00);
        PeriodTimeEvent eventWithoutFrecuency = new PeriodTimeEvent(4, title, description, start, end);
        Frequency frecuencyEvent = new FrequencyDiary(2);
        eventWithoutFrecuency.setEventFrequency(frecuencyEvent);
        eventWithoutFrecuency.setNoRepeat();
        assertFalse(eventWithoutFrecuency.IsRepeated());
        assertNull(eventWithoutFrecuency.nextEventDateTime(start));
    }

    //Post: test to check if it returns the following event on a daily basis.
    @Test
    public void nextEventDayTest(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023,04,16, 12,0);
        LocalDateTime end = start.plusDays(1);
        PeriodTimeEvent event = new PeriodTimeEvent(5, title, description, start, end);
        Frequency frecuencyEvent = new FrequencyDiary(1);
        frecuencyEvent.addDeadline(LocalDate.of(2023,04,20));
        event.setEventFrequency(frecuencyEvent);

        LocalDateTime day1 = event.getStartDateTime();
        LocalDateTime day2 = event.nextEventDateTime(day1);
        assertEquals(day1.plusDays(1), day2);
        LocalDateTime day3 = event.nextEventDateTime(day2);
        assertEquals(day1.plusDays(2), day3);
        LocalDateTime day4 = event.nextEventDateTime(day3);
        assertEquals(day1.plusDays(3), day4);
        LocalDateTime day5 = event.nextEventDateTime(day4);
        assertEquals(day1.plusDays(4), day5);
        LocalDateTime day6 = event.nextEventDateTime(day5);
        assertNull(day6);
    }

    //Post: test to check if it returns the following event on a weekly basis.
    @Test
    public void nextEventWeekTest(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023,04,16, 10,30);
        LocalDateTime end = start.plusDays(1);

        ArrayList<DayOfWeek> array = new ArrayList<DayOfWeek>();
        array.add(DayOfWeek.TUESDAY);
        array.add(DayOfWeek.THURSDAY);
        array.add(DayOfWeek.SATURDAY);
        LocalDateTime tuesday = LocalDateTime.of(2023, 4, 18, 10, 30);
        LocalDateTime thursday = LocalDateTime.of(2023, 4, 20, 10, 30);
        LocalDateTime saturday = LocalDateTime.of(2023, 4, 22, 10, 30);

        Frequency frecuency = new FrequencyWeekly(array);
        frecuency.addDeadline(LocalDate.of(2023,4,25));

        PeriodTimeEvent event = new PeriodTimeEvent(6, title, description, start, end);
        event.setEventFrequency(frecuency);

        LocalDateTime day1 = event.getStartDateTime();
        LocalDateTime day2 = event.nextEventDateTime(day1);
        assertEquals(day2, tuesday);
        LocalDateTime day3 = event.nextEventDateTime(day2);
        assertEquals(day3, thursday);
        LocalDateTime day4 = event.nextEventDateTime(day3);
        assertEquals(day4, saturday);
        LocalDateTime day5 = event.nextEventDateTime(day4);
        assertEquals(day5, tuesday.plusDays(7));
        LocalDateTime day6 = event.nextEventDateTime(day5);
        assertNull(day6);
    }

    //Post: test to check if it returns the following event on annual basis.
    @Test
    public void eventWithRepetitionsAnnual(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023,04,20, 12,00);
        LocalDateTime end = start.plusHours(5);

        Frequency frecuency = new FrequencyAnnual();
        frecuency.addDeadlineWithRepetitions(5, start);

        PeriodTimeEvent event = new PeriodTimeEvent(7, title, description, start, end);
        event.setEventFrequency(frecuency);

        LocalDateTime day = event.getStartDateTime();
        LocalDateTime rep1 = event.nextEventDateTime(day);
        assertEquals(rep1, start.plusYears(1));
        LocalDateTime rep2 = event.nextEventDateTime(rep1);
        assertEquals(rep2, start.plusYears(2));
        LocalDateTime rep3 = event.nextEventDateTime(rep2);
        assertEquals(rep3, start.plusYears(3));
        LocalDateTime rep4 = event.nextEventDateTime(rep3);
        assertEquals(rep4, start.plusYears(4));
        LocalDateTime rep5 = event.nextEventDateTime(rep4);
        assertEquals(rep5, start.plusYears(5));
        LocalDateTime repNull = event.nextEventDateTime(rep5);
        assertNull(repNull);
    }

    //Post: test to check if it returns the following event on a monthly basis.
    @Test
    public void eventWithRepetitionsMonthly(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023,04,20, 12,00);
        LocalDateTime end = start.plusHours(5);
        Frequency frecuency = new FrequencyMonthly();
        frecuency.addDeadlineWithRepetitions(5, start);

        PeriodTimeEvent event = new PeriodTimeEvent(7, title, description, start, end);
        event.setEventFrequency(frecuency);

        LocalDateTime day = event.getStartDateTime();
        LocalDateTime rep1 = event.nextEventDateTime(day);
        assertEquals(rep1, start.plusMonths(1));
        LocalDateTime rep2 = event.nextEventDateTime(rep1);
        assertEquals(rep2, start.plusMonths(2));
        LocalDateTime rep3 = event.nextEventDateTime(rep2);
        assertEquals(rep3, start.plusMonths(3));
        LocalDateTime rep4 = event.nextEventDateTime(rep3);
        assertEquals(rep4, start.plusMonths(4));
        LocalDateTime rep5 = event.nextEventDateTime(rep4);
        assertEquals(rep5, start.plusMonths(5));
        LocalDateTime repNull = event.nextEventDateTime(rep5);
        assertNull(repNull);
    }
}