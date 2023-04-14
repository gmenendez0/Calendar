package org.calendar;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EventTest {

    @Test
    public void verifyEventNoRepeat(){
        Event event = new Event(1, "Titulo", "Primer Evento", LocalDate.now());
        boolean repeat = event.eventIsRepeated();
        assertFalse(repeat);
    }

    @Test
    public void verifyEventYesRepeat(){
        Event event = new Event(2, "Titulo", "Segundo Evento", LocalDate.now());
        event.repeatEvent('M');
        boolean repeat = event.eventIsRepeated();
        assertTrue(repeat);
    }

    @Test
    public void cancelReplay(){
        Event event = new Event(3, "Titulo", "Tercero Evento", LocalDate.now());
        event.repeatEvent('M');
        event.noRepeat();
        boolean repeat = event.eventIsRepeated();
        assertFalse(repeat);
    }

    @Test
    public void repeatEventWithWeek(){
        Event event = new Event(2, "Titulo", "Segundo Evento", LocalDate.of(2023,4,12));
        ArrayList<DayOfWeek> weekDays = new ArrayList<>();
        weekDays.add(DayOfWeek.WEDNESDAY);
        weekDays.add(DayOfWeek.FRIDAY);
        weekDays.add(DayOfWeek.SATURDAY);
        event.repeatEvent('W', weekDays);
        LocalDateTime wednesday = LocalDateTime.of(2023,4,12,00,00);
        LocalDateTime nextDate = event.nextEventDate(wednesday);
        LocalDateTime friday = LocalDateTime.of(2023,4,14,00,00);
        assertEquals(nextDate, friday);
    }

}