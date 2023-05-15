package org.calendar.event;

import org.calendar.event.frequency.*;
import org.junit.Before;
import org.junit.Test;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PeriodTimeEventTest {
    private PeriodTimeEvent periodTimeEvent;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;

    @Before
    public void initialize(){
        String title = "title of event";
        String description = "description of event";
        setDates();
        this.periodTimeEvent = new PeriodTimeEvent(title, description, dateTimeStart, dateTimeEnd);
    }

    private void setDates(){
        this.dateTimeStart = LocalDateTime.of(2023,4,30,12,0,0);
        this.dateTimeEnd = dateTimeStart.plusDays(2);
    }

    //Post: test to check if it returns event duration.
    @Test
    public void verifyDuration(){
        LocalDateTime start = LocalDateTime.of(2023,4,30,12,0,0);
        LocalDateTime end = start.plusDays(2);

        LocalDateTime startResponse = periodTimeEvent.getStartDateTime();
        LocalDateTime endResponse = periodTimeEvent.getEndingDateTime();

        assertEquals(startResponse, start);
        assertEquals(endResponse, end);
    }

    //Post: test to check the change event duration.
    @Test
    public void verifyChangeDuration(){
        LocalDateTime startTest = LocalDateTime.of(2023,4,30,0,0,0);
        LocalDateTime endTest = startTest.plusDays(2);

        periodTimeEvent.setEventStartDateTime(startTest);
        periodTimeEvent.setEventEndingDateTime(endTest);
        LocalDateTime startResponse = periodTimeEvent.getStartDateTime();
        LocalDateTime endResponse = periodTimeEvent.getEndingDateTime();
        setDates();
        assertEquals(startResponse, startTest);
        assertEquals(endResponse, endTest);
    }

    //Post: test to verify if it has no repetition
    @Test
    public void newEventDoesNotRepeat(){
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
        Frequency frequencyEvent = new FrequencyDaily(2, null);
        periodTimeEvent.setEventFrequency(frequencyEvent);
        assertTrue(periodTimeEvent.isRepeated());
    }

    //Post: test to verify if canceling repeats works.
    @Test
    public void eventNoRepeat(){
        Frequency frequencyEvent = new FrequencyDaily(2, null);
        periodTimeEvent.setEventFrequency(frequencyEvent);
        periodTimeEvent.setNoRepeat();
        assertFalse(periodTimeEvent.isRepeated());
        assertNull(periodTimeEvent.getNextEventRegardDateTime(dateTimeStart));
    }

    //Post: test to check if it returns the following event on a daily basis.
    @Test
    public void nextEventDayTest(){
        LocalDate deadline = LocalDate.of(2023, 5,3);
        Frequency frequencyEvent = new FrequencyDaily(1, deadline);
        periodTimeEvent.setEventFrequency(frequencyEvent);

        LocalDateTime day1 = periodTimeEvent.getStartDateTime();
        LocalDateTime day2 = periodTimeEvent.getNextEventRegardDateTime(day1);
        assertEquals(day1.plusDays(1), day2);
        LocalDateTime day3 = periodTimeEvent.getNextEventRegardDateTime(day2);
        assertEquals(day1.plusDays(2), day3);
        LocalDateTime day4 = periodTimeEvent.getNextEventRegardDateTime(day3);
        assertEquals(day1.plusDays(3), day4);
        LocalDateTime day5 = periodTimeEvent.getNextEventRegardDateTime(day4);
        assertNull(day5);
    }

    //Post: test to check if it returns the following event on a weekly basis.
    @Test
    public void nextEventWeekTest(){
        List<DayOfWeek> array = new ArrayList<>();
        array.add(DayOfWeek.TUESDAY);
        array.add(DayOfWeek.THURSDAY);
        array.add(DayOfWeek.SATURDAY);
        LocalDateTime tuesday = LocalDateTime.of(2023, 5, 2, 10, 30);
        LocalDateTime thursday = LocalDateTime.of(2023, 5, 4, 10, 30);
        LocalDateTime saturday = LocalDateTime.of(2023, 5, 6, 10, 30);
        LocalDate deadline = LocalDate.of(2023,5,9);
        periodTimeEvent.setEventStartDateTime(tuesday);
        Frequency frequency = new FrequencyWeekly(array, deadline);
        periodTimeEvent.setEventFrequency(frequency);

        LocalDateTime day1 = periodTimeEvent.getStartDateTime();
        assertEquals(tuesday, day1);
        LocalDateTime day2 = periodTimeEvent.getNextEventRegardDateTime(day1);
        assertEquals(thursday, day2);
        LocalDateTime day3 = periodTimeEvent.getNextEventRegardDateTime(day2);
        assertEquals(saturday, day3);
        LocalDateTime day4 = periodTimeEvent.getNextEventRegardDateTime(day3);
        assertEquals(tuesday.plusDays(7), day4);
        LocalDateTime day6 = periodTimeEvent.getNextEventRegardDateTime(day4);
        assertNull(day6);
    }

    //Post: test to check if it returns the following event on annual basis.
    @Test
    public void eventWithRepetitionsAnnual(){
        Frequency frequency = new FrequencyAnnual(null);
        frequency.addDeadlineWithRepetitions(3, dateTimeStart);

        periodTimeEvent.setEventFrequency(frequency);

        LocalDateTime day = periodTimeEvent.getStartDateTime();
        LocalDateTime rep1 = periodTimeEvent.getNextEventRegardDateTime(day);
        assertEquals(rep1, dateTimeStart.plusYears(1));
        LocalDateTime rep2 = periodTimeEvent.getNextEventRegardDateTime(rep1);
        assertEquals(rep2, dateTimeStart.plusYears(2));
        LocalDateTime rep3 = periodTimeEvent.getNextEventRegardDateTime(rep2);
        assertEquals(rep3, dateTimeStart.plusYears(3));
        LocalDateTime repNull = periodTimeEvent.getNextEventRegardDateTime(rep3);
        assertNull(repNull);
    }

    //Post: test to check if it returns the following event on a monthly basis.
    @Test
    public void eventWithRepetitionsMonthly(){
        Frequency frequency = new FrequencyMonthly(null);
        frequency.addDeadlineWithRepetitions(5, dateTimeStart);

        periodTimeEvent.setEventFrequency(frequency);

        LocalDateTime day = periodTimeEvent.getStartDateTime();
        LocalDateTime rep1 = periodTimeEvent.getNextEventRegardDateTime(day);
        assertEquals(rep1, dateTimeStart.plusMonths(1));
        LocalDateTime rep2 = periodTimeEvent.getNextEventRegardDateTime(rep1);
        assertEquals(rep2, dateTimeStart.plusMonths(2));
        LocalDateTime rep3 = periodTimeEvent.getNextEventRegardDateTime(rep2);
        assertEquals(rep3, dateTimeStart.plusMonths(3));
        LocalDateTime rep4 = periodTimeEvent.getNextEventRegardDateTime(rep3);
        assertEquals(rep4, dateTimeStart.plusMonths(4));
        LocalDateTime rep5 = periodTimeEvent.getNextEventRegardDateTime(rep4);
        assertEquals(rep5, dateTimeStart.plusMonths(5));
        LocalDateTime repNull = periodTimeEvent.getNextEventRegardDateTime(rep5);
        assertNull(repNull);
    }

    @Test
    public void obtainLastRepetitionEvent(){
        Frequency frequency = new FrequencyMonthly(null);
        frequency.addDeadlineWithRepetitions(5, dateTimeStart);
        periodTimeEvent.setEventFrequency(frequency);

        LocalDateTime last = LocalDateTime.of(2023, 9,30, 12,0);

        assertEquals(last, periodTimeEvent.getLastRepetitionEndingDateTime());
    }

    @Test
    public void theImmediateNextEventStartDateTime(){

        Frequency frequency = new FrequencyDaily(5, null);
        periodTimeEvent.setEventFrequency(frequency);

        LocalDateTime anyDate = LocalDateTime.of(2023, 5,7, 15,0);
        LocalDateTime immediateDateEventStart = LocalDateTime.of(2023, 5,10, 12,0);

        LocalDateTime response = periodTimeEvent.getNextRepetitionStartDateTime(anyDate);

        assertEquals(immediateDateEventStart, response);
    }

    @Test
     public void theImmediateNextEventEndDateTime(){
        Frequency frequency = new FrequencyDaily(5, null);
        periodTimeEvent.setEventFrequency(frequency);

        LocalDateTime anyDate = LocalDateTime.of(2023, 5,7, 15,0);
        LocalDateTime immediateDateEventEnd = LocalDateTime.of(2023, 5,12, 12,0);

        LocalDateTime response = periodTimeEvent.getNextRepetitionEndingDateTime(anyDate);

        assertEquals(immediateDateEventEnd, response);
    }
}