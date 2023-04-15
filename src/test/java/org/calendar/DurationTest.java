package org.calendar;

import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class DurationTest {

    @Test
    public void theSameDay(){
        LocalDate day = LocalDate.now();
        LocalDateTime dayStart = day.atStartOfDay();
        LocalDateTime dayEnd = day.atTime(23,59,59);
        var duration = new Duration(day);
        LocalDateTime start = duration.whenItStarts();
        LocalDateTime end = duration.whenItEnds();

        assertEquals(dayStart, start);
        assertEquals(dayEnd, end);
    }

    @Test
    public void durationMoreThanADay(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tenDaysLater = now.plus(10, ChronoUnit.DAYS);
        var duration = new Duration(now, tenDaysLater);
        LocalDateTime start = duration.whenItStarts();
        LocalDateTime end = duration.whenItEnds();

        assertEquals(now, start);
        assertEquals(tenDaysLater, end);
    }

    @Test
    public void changeDurationOfOneToMore(){
        LocalDate now1 = LocalDate.now();
        var duration = new Duration(now1);
        LocalDateTime now2 = LocalDateTime.now();
        LocalDateTime tenDaysLater = now2.plus(10, ChronoUnit.DAYS);
        duration.changeDuration(now2, tenDaysLater);

        LocalDateTime start = duration.whenItStarts();
        LocalDateTime end = duration.whenItEnds();

        assertEquals(now2, start);
        assertEquals(tenDaysLater, end);
    }

    @Test
    public void changeDurationOfMoreToOne(){
        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime tenDaysLater = now1.plus(10, ChronoUnit.DAYS);
        LocalDate now2 = LocalDate.now();
        var duration = new Duration(now1, tenDaysLater);

        duration.changeDuration(now2);
        LocalDateTime start = duration.whenItStarts();
        LocalDateTime end = duration.whenItEnds();

        assertEquals(now2.atTime(0,0,0), start);
        assertEquals(now2.atTime(23,59,59), end);
    }
}