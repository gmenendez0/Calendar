package org.models.calendar.visitor.getAppointmentsBetweenVisitor;

import org.models.calendar.appointment.Appointment;
import org.models.calendar.task.WholeDayTask;
import org.junit.Before;
import org.junit.Test;
import org.models.calendar.visitor.get_appointments_between.GetAppointmentsBetweenVisitorImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WholeDayTaskVisitorTest{
    private GetAppointmentsBetweenVisitorImpl appointmentVisitor;
    private List<Appointment> selectedAppointments = new ArrayList<>();
    private LocalDateTime firstDateTime;
    private LocalDateTime secondDateTime;

    @Before
    public void initialize() {
        appointmentVisitor = new GetAppointmentsBetweenVisitorImpl();
    }

    //Tests when taskDateTime is not within firstDateTime and secondDateTime range.
    @Test
    public void taskDateTimeOutOfBounds(){
        var outOfRangeTask = new WholeDayTask("title", "desc", LocalDate.of(2019, 1, 15));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,31,12,0,0);

        selectedAppointments = appointmentVisitor.visitWholeDayTask(outOfRangeTask, firstDateTime, secondDateTime);

        assertEquals(0, selectedAppointments.size());
    }

    //Tests when taskDateTime is between firstDateTime and secondDateTime.
    @Test
    public void taskDateTimeBetweenDates(){
        var betweenDatesTask = new WholeDayTask("title", "desc", LocalDate.of(2020, 1, 15));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,31,12,0,0);

        selectedAppointments = appointmentVisitor.visitWholeDayTask(betweenDatesTask, firstDateTime, secondDateTime);

        assertEquals(betweenDatesTask, selectedAppointments.get(0));
    }

    //Tests when taskDateTime == firstDateTime.
    @Test
    public void taskDateTimeEqualsFirstDateTime(){
        var matchesFirstDateTimeTask = new WholeDayTask("title", "desc", LocalDate.of(2020, 1, 15));
        firstDateTime = LocalDateTime.of(2020,1,15,0,0,0);
        secondDateTime = LocalDateTime.of(2020,1,31,12,0,0);

        selectedAppointments = appointmentVisitor.visitWholeDayTask(matchesFirstDateTimeTask, firstDateTime, secondDateTime);

        assertEquals(matchesFirstDateTimeTask, selectedAppointments.get(0));
    }

    //Tests when taskDateTime == secondDateTime.
    @Test
    public void taskDateTimeEqualsSecondDateTime(){
        var matchesSecondDateTimeTask = new WholeDayTask("title", "desc", LocalDate.of(2020, 1, 31));
        firstDateTime = LocalDateTime.of(2020,1,15,0,0,0);
        secondDateTime = LocalDateTime.of(2020,1,31,0,0,0);

        selectedAppointments = appointmentVisitor.visitWholeDayTask(matchesSecondDateTimeTask, firstDateTime, secondDateTime);

        assertEquals(matchesSecondDateTimeTask, selectedAppointments.get(0));
    }
}
