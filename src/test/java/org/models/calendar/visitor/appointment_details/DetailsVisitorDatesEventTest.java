package org.models.calendar.visitor.appointment_details;

import org.junit.Test;
import org.models.calendar.event.Event;
import org.models.calendar.event.PeriodTimeEvent;
import org.models.calendar.event.WholeDayEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DetailsVisitorDatesEventTest {
    private final AppointmentDetailsVisitor visitor = new AppointmentDetailsVisitorImpl();

    @Test
    public void visitorInPeriodTimeEventTest(){
        String expected = "The event starts on 2023-06-13T23:05 and ends on 2023-06-14T00:00.";
        LocalDateTime start = LocalDateTime.of(2023, 6, 13, 23, 5);
        LocalDateTime end = LocalDateTime.of(2023, 6, 14, 0, 0);
        Event event = new PeriodTimeEvent("Title", "Description", start, end);
        String response = event.acceptVisitorDetailsDates(visitor);
        assertEquals(expected, response);
    }

    @Test
    public void visitorInWholeDayEventTest(){
        String expected = "The event starts on 2023-06-14T00:00 and ends on 2023-06-14T23:59:59.";
        LocalDate now = LocalDate.of(2023, 6, 14);
        Event event = new WholeDayEvent("Title", "Description", now);
        String response = event.acceptVisitorDetailsDates(visitor);
        assertEquals(expected, response);
    }
}