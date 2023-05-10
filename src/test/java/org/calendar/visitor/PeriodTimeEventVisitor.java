package org.calendar.visitor;

import org.calendar.appointment.Appointment;
import org.calendar.event.PeriodTimeEvent;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PeriodTimeEventVisitor{
    private AppointmentsVisitor appointmentVisitor;
    private List<Appointment> selectedAppointments = new ArrayList<>();
    private LocalDateTime firstDateTime;
    private LocalDateTime secondDateTime;

    @Before
    public void initialize() {
        appointmentVisitor = new AppointmentsVisitor();
    }

    //* ***** NON-REPEATED-EVENTS-TESTS *****

    //Tests when event takes place before given dates.
    @Test
    public void EventOutOfBoundsBeforeDates(){
        var outOfBoundsEvent = new PeriodTimeEvent("title", "desc", LocalDateTime.of(2020,1,1,12,0,0), LocalDateTime.of(2020,1,1,16,0,0));
        firstDateTime = LocalDateTime.of(2020,1,1,16,1,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,1,0);

        selectedAppointments = appointmentVisitor.visitPeriodTimeEvent(outOfBoundsEvent, firstDateTime, secondDateTime);

        assertEquals(0, selectedAppointments.size());
    }

    //Tests when event ends at firstDateTime.
    @Test
    public void EventEndsAtFirstDateTime(){
        var matchesFirstTimeEvent = new PeriodTimeEvent("title", "desc", LocalDateTime.of(2020,1,1,12,0,0), LocalDateTime.of(2020,1,2,16,1,0));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,1,0);

        selectedAppointments = appointmentVisitor.visitPeriodTimeEvent(matchesFirstTimeEvent, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event starts at firstDateTime.
    @Test
    public void EventStartsAtFirstDateTime(){
        var matchesFirstTimeEvent = new PeriodTimeEvent("title", "desc", LocalDateTime.of(2020,1,1,12,0,0), LocalDateTime.of(2020,1,1,16,0,0));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,1,0);

        selectedAppointments = appointmentVisitor.visitPeriodTimeEvent(matchesFirstTimeEvent, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event is between first and second dateTime.
    @Test
    public void EventIsBetweenDates(){
        var isBetweenDatesEvent = new PeriodTimeEvent("title", "desc", LocalDateTime.of(2020,1,1,12,0,0), LocalDateTime.of(2020,1,1,16,0,0));
        firstDateTime = LocalDateTime.of(2019,12,31,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,1,0);

        selectedAppointments = appointmentVisitor.visitPeriodTimeEvent(isBetweenDatesEvent, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event ends at secondDateTime.
    @Test
    public void EventEndsAtSecondDateTime(){
        var matchesSecondTimeEvent = new PeriodTimeEvent("title", "desc", LocalDateTime.of(2019,12,31,12,0,0), LocalDateTime.of(2020,1,2,16,0,0));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,0,0);

        selectedAppointments = appointmentVisitor.visitPeriodTimeEvent(matchesSecondTimeEvent, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event starts at secondDateTime.
    @Test
    public void EventStartsAtSecondDateTime(){
        var matchesSecondTimeEvent = new PeriodTimeEvent("title", "desc", LocalDateTime.of(2020,1,2,16,0,0), LocalDateTime.of(2020,1,3,16,0,0));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,0,0);

        selectedAppointments = appointmentVisitor.visitPeriodTimeEvent(matchesSecondTimeEvent, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event takes place after given dates.
    @Test
    public void EventOutOfBoundsAfterDates(){
        var outOfBoundsEvent = new PeriodTimeEvent("title", "desc", LocalDateTime.of(2021,1,2,16,0,0), LocalDateTime.of(2021,1,3,16,0,0));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,0,0);

        selectedAppointments = appointmentVisitor.visitPeriodTimeEvent(outOfBoundsEvent, firstDateTime, secondDateTime);

        assertEquals(0, selectedAppointments.size());
    }

    //Tests when event starts before firstDateTime and ends between firstDateTime and secondDateTime.
    @Test
    public void EventStartsBeforeEndsBetween(){
        var event = new PeriodTimeEvent("title", "desc", LocalDateTime.of(2019,1,2,16,0,0), LocalDateTime.of(2020,1,1,16,0,0));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,0,0);

        selectedAppointments = appointmentVisitor.visitPeriodTimeEvent(event, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event starts between firstDateTime and secondDateTime and ends after secondDateTime.
    @Test
    public void EventStartsBetweenEndsAfter(){
        var event = new PeriodTimeEvent("title", "desc", LocalDateTime.of(2020,1,1,12,35,0), LocalDateTime.of(2021,1,1,16,0,0));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,0,0);

        selectedAppointments = appointmentVisitor.visitPeriodTimeEvent(event, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event starts before firstDateTime and ends after secondDateTime.
    @Test
    public void EventStartsBeforeEndsAfter(){
        var event = new PeriodTimeEvent("title", "desc", LocalDateTime.of(2019,1,1,12,35,0), LocalDateTime.of(2021,1,1,16,0,0));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,0,0);

        selectedAppointments = appointmentVisitor.visitPeriodTimeEvent(event, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //* ***** REPEATED EVENTS TESTS *****
}


