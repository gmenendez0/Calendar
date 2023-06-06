package org.backEnd.calendar.visitor;

import org.backEnd.calendar.appointment.Appointment;
import org.backEnd.calendar.task.ExpirationTimeTask;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExpirationTimeTaskVisitorTest{
    private AppointmentsVisitor appointmentVisitor;
    private List<Appointment> selectedAppointments = new ArrayList<>();
    private LocalDateTime firstDateTime;
    private LocalDateTime secondDateTime;

    @Before
    public void initialize() {
        appointmentVisitor = new AppointmentsVisitor();
    }

    //Tests when taskDateTime is not within firstDateTime and secondDateTime range.
    @Test
    public void taskDateTimeOutOfBounds(){
        var outOfRangeTask = new ExpirationTimeTask("title", "desc", LocalDateTime.of(2019,1,15,0,0,0));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,31,12,0,0);

        selectedAppointments = appointmentVisitor.visitExpirationTimeTask(outOfRangeTask, firstDateTime, secondDateTime);

        assertEquals(0, selectedAppointments.size());
    }

    //Tests when taskDateTime is between firstDateTime and secondDateTime.
    @Test
    public void taskDateTimeBetweenDates(){
        var betweenDatesTask = new ExpirationTimeTask("title", "desc", LocalDateTime.of(2020,1,15,0,0,0));
        firstDateTime = LocalDateTime.of(2020,1,1,0,0,0);
        secondDateTime = LocalDateTime.of(2020,1,31,0,0,0);

        selectedAppointments = appointmentVisitor.visitExpirationTimeTask(betweenDatesTask, firstDateTime, secondDateTime);

        assertEquals(betweenDatesTask, selectedAppointments.get(0));
    }

    //Tests when taskDateTime == firstDateTime.
    @Test
    public void taskDateTimeEqualsFirstDateTime(){
        var matchesFirstDateTimeTask = new ExpirationTimeTask("title", "desc", LocalDateTime.of(2020,1,15,0,0,0));
        firstDateTime = LocalDateTime.of(2020,1,15,0,0,0);
        secondDateTime = LocalDateTime.of(2020,1,31,12,0,0);

        selectedAppointments = appointmentVisitor.visitExpirationTimeTask(matchesFirstDateTimeTask, firstDateTime, secondDateTime);

        assertEquals(matchesFirstDateTimeTask, selectedAppointments.get(0));
    }

    //Tests when taskDateTime == secondDateTime.
    @Test
    public void taskDateTimeEqualsSecondDateTime(){
        var matchesSecondDateTimeTask = new ExpirationTimeTask("title", "desc", LocalDateTime.of(2020,1,31,0,0,0));
        firstDateTime = LocalDateTime.of(2020,1,15,0,0,0);
        secondDateTime = LocalDateTime.of(2020,1,31,0,0,0);

        selectedAppointments = appointmentVisitor.visitExpirationTimeTask(matchesSecondDateTimeTask, firstDateTime, secondDateTime);

        assertEquals(matchesSecondDateTimeTask, selectedAppointments.get(0));
    }
}