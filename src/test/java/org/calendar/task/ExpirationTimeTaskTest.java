package org.calendar.task;

import org.calendar.appointment.Appointment;
import org.calendar.visitor.AppointmentsVisitor;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class ExpirationTimeTaskTest {
    final int ONE_DAY = 1;

    private LocalDateTime dateTime;
    private ExpirationTimeTask task;

    @Before
    public void inicialice(){
        dateTime = LocalDateTime.of(2020,1,1,12,0,0);
        task = new ExpirationTimeTask("title", "description", dateTime);
    }

    //Post: Tests that setExpirationDateTime sets the value correctly.
    @Test
    public void setExpirationDateTime() {
        LocalDateTime yesterday = dateTime.minusDays(ONE_DAY);

        task.setExpirationDateTime(yesterday);
        var time = task.getExpirationDateTime();

        assertEquals(yesterday, time);
    }

    //Post: Tests that getExpirationDateTime returns the correct value.
    @Test
    public void getExpirationDateTime() {
        var expirationDateTime = task.getExpirationDateTime();

        assertEquals(dateTime, expirationDateTime);
    }

    //Post: Tests that acceptVisitor method behaves correctly.
    @Test
    public void acceptVisitor(){
        var visitor = new AppointmentsVisitor();
        var firstDateTime = LocalDateTime.of(2019,1,15,0,0,0);
        var secondDateTime = LocalDateTime.of(2021,1,31,12,0,0);

        List<Appointment> selectedAppointments = task.acceptVisitor(visitor, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }
}