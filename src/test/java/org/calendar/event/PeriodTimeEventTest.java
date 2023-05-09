package org.calendar.event;

import org.calendar.event.frequency.*;
import org.junit.Test;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PeriodTimeEventTest {

    //Post: test to check if it returns event duration.
    @Test
    public void verifyDuration(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023,4,30,12,0,0);
        LocalDateTime end = start.plusDays(2);
        PeriodTimeEvent newEvent = new PeriodTimeEvent(title, description, start, end);

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
        LocalDateTime start1 = LocalDateTime.of(2023,4,30,12,0,0);
        LocalDateTime end1 = start1.plusDays(2);
        LocalDateTime start2 = LocalDateTime.of(2023,4,30,0,0,0);
        LocalDateTime end2 = start2.plusDays(2);

        PeriodTimeEvent newEvent = new PeriodTimeEvent(title, description, start1, end1);
        newEvent.setEventStartDateTime(start2);
        newEvent.setEventEndingDateTime(end2);
        LocalDateTime startResponse = newEvent.getStartDateTime();
        LocalDateTime endResponse = newEvent.getEndingDateTime();

        assertEquals(startResponse, start2);
        assertEquals(endResponse, end2);
    }

    //Post: test to verify if it has no repetition
    @Test
    public void eventDoesNotRepeat(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023,4,30,12,0,0);
        LocalDateTime end = start.plusDays(2);
        PeriodTimeEvent eventWithoutFrequency = new PeriodTimeEvent(title, description, start, end);
        assertFalse(eventWithoutFrequency.isRepeated());
    }

    //Post: test to verify if it has repetition
    @Test
    public void eventHaveReplay(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023,4,30,12,0,0);
        LocalDateTime end = start.plusDays(2);
        PeriodTimeEvent eventWithFrequency = new PeriodTimeEvent(title, description, start, end);
        Frequency frequencyEvent = new FrequencyDaily(2);
        eventWithFrequency.setEventFrequency(frequencyEvent);
        assertTrue(eventWithFrequency.isRepeated());
    }

    //Post: test to verify if canceling repeats works.
    @Test
    public void eventNoRepeat(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023,4,30,12,0,0);
        LocalDateTime end = start.plusDays(2);
        PeriodTimeEvent eventWithoutFrequency = new PeriodTimeEvent(title, description, start, end);
        Frequency frequencyEvent = new FrequencyDaily(2);
        eventWithoutFrequency.setEventFrequency(frequencyEvent);
        eventWithoutFrequency.setNoRepeat();
        assertFalse(eventWithoutFrequency.isRepeated());
        assertNull(eventWithoutFrequency.getNextEventRegardDateTime(start));
    }

    //Post: test to check if it returns the following event on a daily basis.
    @Test
    public void nextEventDayTest(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023, 4,16, 12,0);
        LocalDateTime end = start.plusDays(1);
        PeriodTimeEvent event = new PeriodTimeEvent(title, description, start, end);
        Frequency frequencyEvent = new FrequencyDaily(1);
        LocalDate deadline = LocalDate.of(2023, 4,20);
        frequencyEvent.addDeadline(deadline);
        event.setEventFrequency(frequencyEvent);

        LocalDateTime day1 = event.getStartDateTime();
        LocalDateTime day2 = event.getNextEventRegardDateTime(day1);
        assertEquals(day1.plusDays(1), day2);
        LocalDateTime day3 = event.getNextEventRegardDateTime(day2);
        assertEquals(day1.plusDays(2), day3);
        LocalDateTime day4 = event.getNextEventRegardDateTime(day3);
        assertEquals(day1.plusDays(3), day4);
        LocalDateTime day5 = event.getNextEventRegardDateTime(day4);
        assertEquals(day1.plusDays(4), day5);
        LocalDateTime day6 = event.getNextEventRegardDateTime(day5);
        assertNull(day6);
    }

    //Post: test to check if it returns the following event on a weekly basis.
    @Test
    public void nextEventWeekTest(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023, 4,15, 10,30);
        LocalDateTime end = start.plusDays(1);

        ArrayList<DayOfWeek> array = new ArrayList<>();
        array.add(DayOfWeek.TUESDAY);
        array.add(DayOfWeek.THURSDAY);
        array.add(DayOfWeek.SATURDAY);
        LocalDateTime tuesday = LocalDateTime.of(2023, 4, 18, 10, 30);
        LocalDateTime thursday = LocalDateTime.of(2023, 4, 20, 10, 30);
        LocalDateTime saturday = LocalDateTime.of(2023, 4, 22, 10, 30);

        Frequency frequency = new FrequencyWeekly(array);
        frequency.addDeadline(LocalDate.of(2023,4,25));

        PeriodTimeEvent event = new PeriodTimeEvent(title, description, start, end);
        event.setEventFrequency(frequency);

        LocalDateTime day1 = event.getStartDateTime();
        LocalDateTime day2 = event.getNextEventRegardDateTime(day1);
        assertEquals(day2, tuesday);
        LocalDateTime day3 = event.getNextEventRegardDateTime(day2);
        assertEquals(day3, thursday);
        LocalDateTime day4 = event.getNextEventRegardDateTime(day3);
        assertEquals(day4, saturday);
        LocalDateTime day5 = event.getNextEventRegardDateTime(day4);
        assertEquals(day5, tuesday.plusDays(7));
        LocalDateTime day6 = event.getNextEventRegardDateTime(day5);
        assertNull(day6);
    }

    //Post: test to check if it returns the following event on annual basis.
    @Test
    public void eventWithRepetitionsAnnual(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023, 4,20, 12,0);
        LocalDateTime end = start.plusHours(5);

        Frequency frequency = new FrequencyAnnual();
        frequency.addDeadlineWithRepetitions(5, start);

        PeriodTimeEvent event = new PeriodTimeEvent(title, description, start, end);
        event.setEventFrequency(frequency);

        LocalDateTime day = event.getStartDateTime();
        LocalDateTime rep1 = event.getNextEventRegardDateTime(day);
        assertEquals(rep1, start.plusYears(1));
        LocalDateTime rep2 = event.getNextEventRegardDateTime(rep1);
        assertEquals(rep2, start.plusYears(2));
        LocalDateTime rep3 = event.getNextEventRegardDateTime(rep2);
        assertEquals(rep3, start.plusYears(3));
        LocalDateTime rep4 = event.getNextEventRegardDateTime(rep3);
        assertEquals(rep4, start.plusYears(4));
        LocalDateTime rep5 = event.getNextEventRegardDateTime(rep4);
        assertEquals(rep5, start.plusYears(5));
        LocalDateTime repNull = event.getNextEventRegardDateTime(rep5);
        assertNull(repNull);
    }

    //Post: test to check if it returns the following event on a monthly basis.
    @Test
    public void eventWithRepetitionsMonthly(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023, 4,20, 12,0);
        LocalDateTime end = start.plusHours(5);
        Frequency frequency = new FrequencyMonthly();
        frequency.addDeadlineWithRepetitions(5, start);

        PeriodTimeEvent event = new PeriodTimeEvent(title, description, start, end);
        event.setEventFrequency(frequency);

        LocalDateTime day = event.getStartDateTime();
        LocalDateTime rep1 = event.getNextEventRegardDateTime(day);
        assertEquals(rep1, start.plusMonths(1));
        LocalDateTime rep2 = event.getNextEventRegardDateTime(rep1);
        assertEquals(rep2, start.plusMonths(2));
        LocalDateTime rep3 = event.getNextEventRegardDateTime(rep2);
        assertEquals(rep3, start.plusMonths(3));
        LocalDateTime rep4 = event.getNextEventRegardDateTime(rep3);
        assertEquals(rep4, start.plusMonths(4));
        LocalDateTime rep5 = event.getNextEventRegardDateTime(rep4);
        assertEquals(rep5, start.plusMonths(5));
        LocalDateTime repNull = event.getNextEventRegardDateTime(rep5);
        assertNull(repNull);
    }

    @Test
    public void obtainLastRepetitionEvent(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023, 4,20, 12,0);
        LocalDateTime end = start.plusHours(5);

        Frequency frequency = new FrequencyMonthly();
        frequency.addDeadlineWithRepetitions(5, start);
        PeriodTimeEvent event = new PeriodTimeEvent(title, description, start, end);
        event.setEventFrequency(frequency);

        LocalDateTime last = LocalDateTime.of(2023, 9,20, 17,0);

        assertEquals(last, event.getLastRepetitionEndingDateTime());
    }

    @Test
    public void theImmediateNextEventStartDateTime(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023, 4,20, 12,0);
        LocalDateTime end = start.plusHours(5);

        Frequency frequency = new FrequencyDaily(5);
        frequency.addDeadlineWithRepetitions(5, start);
        PeriodTimeEvent event = new PeriodTimeEvent(title, description, start, end);
        event.setEventFrequency(frequency);

        LocalDateTime anyDate = LocalDateTime.of(2023, 4,27, 15,0);
        LocalDateTime immediateDateEventStart = LocalDateTime.of(2023, 4,30, 12,0);

        LocalDateTime response = event.getNextStartDateTime(anyDate);

        assertEquals(immediateDateEventStart, response);
    }

    @Test
    public void theImmediateNextEventEndDateTime(){
        String title = "title of event";
        String description = "description of event";
        LocalDateTime start = LocalDateTime.of(2023, 4,20, 12,0);
        LocalDateTime end = start.plusHours(5);

        Frequency frequency = new FrequencyDaily(5);
        frequency.addDeadlineWithRepetitions(5, start);
        PeriodTimeEvent event = new PeriodTimeEvent(title, description, start, end);
        event.setEventFrequency(frequency);

        LocalDateTime anyDate = LocalDateTime.of(2023, 4,27, 15,0);
        LocalDateTime immediateDateEventEnd = LocalDateTime.of(2023, 4,30, 17,0);

        LocalDateTime response = event.getNextEndingDateTime(anyDate);

        assertEquals(immediateDateEventEnd, response);
    }
}