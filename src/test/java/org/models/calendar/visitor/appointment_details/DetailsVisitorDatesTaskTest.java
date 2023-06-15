package org.models.calendar.visitor.appointment_details;

import org.junit.Test;
import org.models.calendar.event.Event;
import org.models.calendar.event.PeriodTimeEvent;
import org.models.calendar.event.WholeDayEvent;
import org.models.calendar.task.ExpirationTimeTask;
import org.models.calendar.task.Task;
import org.models.calendar.task.WholeDayTask;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class DetailsVisitorDatesTaskTest {
    private final AppointmentDetailsVisitor visitor = new AppointmentDetailsVisitorImpl();

    @Test
    public void visitorInExpirationTimeTaskTest(){
        String expected = "The task expires on 2023-06-14T00:00.";
        LocalDateTime expiration = LocalDateTime.of(2023, 6, 14, 00,00);
        Task task = new ExpirationTimeTask("Title", "Description", expiration);
        String response = task.acceptVisitorDetailsDates(visitor);
        assertEquals(expected, response);
    }

    @Test
    public void visitorInWholeDayTaskTest(){
        String expected = "The task expires on 2023-06-15T23:59:59.";
        LocalDate now = LocalDate.of(2023, 6, 15);
        Task task = new WholeDayTask("Title", "Description", now);
        String response = task.acceptVisitorDetailsDates(visitor);
        assertEquals(expected, response);
    }
}