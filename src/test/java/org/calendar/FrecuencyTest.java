package org.calendar;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FrecuencyTest {
    @Test
    public void frecuencyType(){
        char type = 'W';
        var frecuency = new Frecuency(type);
        assertEquals(type, frecuency.getFrecuency());
    }

    @Test
    public void diaryWithCommonInterval(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plus(20, ChronoUnit.DAYS);
        int interval = 20;
        char type = 'D';
        var frecuency = new Frecuency(type, interval);
        LocalDateTime laterOfClass = frecuency.nextDate(now);
        assertEquals(later, laterOfClass);
    }

    @Test
    public void diaryWithZeroInterval(){
        LocalDateTime now = LocalDateTime.now();
        int interval = 0;
        char type = 'D';
        var frecuency = new Frecuency(type, interval);
        LocalDateTime laterOfClass = frecuency.nextDate(now);
        assertEquals(laterOfClass, now);
    }

    @Test
    public void annualFrecuency(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plus(1, ChronoUnit.YEARS);
        char type = 'Y';
        var frecuency = new Frecuency(type);
        LocalDateTime laterOfClass = frecuency.nextDate(now);
        assertEquals(later, laterOfClass);
    }

    @Test
    public void monthlyFrecuency(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plus(1, ChronoUnit.MONTHS);
        char type = 'M';
        var frecuency = new Frecuency(type);
        LocalDateTime laterOfClass = frecuency.nextDate(now);
        assertEquals(later, laterOfClass);
    }

//    @Test
//    public void similarityBetweenDailyAndAnnual(){
//        LocalDateTime now = LocalDateTime.now();
//
//        //366 porque el los días se empiezan a contar
//        int interval = 366;
//        char typeD = 'D';
//        var frecuencyDay = new Frecuency(typeD, interval);
//        LocalDateTime laterDay = frecuencyDay.nextDate(now);
//
//        char typeY = 'Y';
//        var frecuencyYear = new Frecuency(typeY);
//        LocalDateTime laterYear = frecuencyYear.nextDate(now);
//
//        assertEquals(laterDay, laterYear);
//    }

    @Test
    public void weeklyFrecuency(){
        ArrayList<DayOfWeek> array = new ArrayList<DayOfWeek>();
        array.add(DayOfWeek.MONDAY);
        array.add(DayOfWeek.WEDNESDAY);
        array.add(DayOfWeek.FRIDAY);
        LocalDateTime wednesday = LocalDateTime.of(2023, 4, 12, 10, 30);
        LocalDateTime friday = LocalDateTime.of(2023, 4, 14, 10, 30);
        char type = 'W';
        var frecuency = new Frecuency(type, array);

        LocalDateTime response = frecuency.nextDate(wednesday);

        assertEquals(friday, response);
    }

    @Test
    public void changeFrecuencyWeeklyToDaily(){
        ArrayList<DayOfWeek> array = new ArrayList<DayOfWeek>();
        array.add(DayOfWeek.MONDAY);
        array.add(DayOfWeek.WEDNESDAY);
        array.add(DayOfWeek.FRIDAY);
        char type = 'W';
        var frecuency = new Frecuency(type, array);
        char newType = 'D';
        int interval = 2;
        frecuency.changeFrecuency(newType, interval);

        LocalDateTime wednesday = LocalDateTime.of(2023, 4, 12, 10, 30);
        LocalDateTime friday = LocalDateTime.of(2023, 4, 14, 10, 30);

        LocalDateTime response = frecuency.nextDate(wednesday);

        assertEquals(friday, response);
    }

    @Test
    public void changeFrecuencyMonthlyToAnnual(){
        char type = 'Y';
        var frecuency = new Frecuency(type);
        char newType = 'M';
        frecuency.changeFrecuency(newType);

        LocalDateTime day = LocalDateTime.of(2023, 4, 13, 10, 30);
        LocalDateTime nextDay = LocalDateTime.of(2023, 5, 13, 10, 30);

        LocalDateTime response = frecuency.nextDate(day);

        assertEquals(nextDay, response);
    }
}