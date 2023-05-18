package org.calendar.visitor;

import org.calendar.appointment.Appointment;
import org.calendar.event.WholeDayEvent;
import org.calendar.event.frequency.FrequencyAnnual;
import org.calendar.event.frequency.FrequencyDaily;
import org.calendar.event.frequency.FrequencyMonthly;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WholeDayEventVisitorTest{
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
        var outOfBoundsEvent = new WholeDayEvent("title", "desc", LocalDate.of(2019, 1, 1));
        firstDateTime = LocalDateTime.of(2020,1,1,16,1,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,1,0);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(outOfBoundsEvent, firstDateTime, secondDateTime);

        assertEquals(0, selectedAppointments.size());
    }

    //Tests when event ends at firstDateTime.
    @Test
    public void EventEndsAtFirstDateTime(){
        var matchesFirstTimeEvent = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 1));
        firstDateTime = LocalDateTime.of(2020,1,1,23,59,59);
        secondDateTime = LocalDateTime.of(2020,1,2,23,59,59);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(matchesFirstTimeEvent, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event starts at firstDateTime.
    @Test
    public void EventStartsAtFirstDateTime(){
        var matchesFirstTimeEvent = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 1));
        firstDateTime = LocalDateTime.of(2020,1,1,0,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,1,0);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(matchesFirstTimeEvent, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event is between first and second dateTime.
    @Test
    public void EventIsBetweenDates(){
        var isBetweenDatesEvent = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 1));
        firstDateTime = LocalDateTime.of(2019,12,31,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,1,0);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(isBetweenDatesEvent, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event ends at secondDateTime.
    @Test
    public void EventEndsAtSecondDateTime(){
        var matchesSecondTimeEvent = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 1));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,1,23,59,59);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(matchesSecondTimeEvent, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event starts at secondDateTime.
    @Test
    public void EventStartsAtSecondDateTime(){
        var matchesSecondTimeEvent = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 2));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,23,59,59);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(matchesSecondTimeEvent, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event takes place after given dates.
    @Test
    public void EventOutOfBoundsAfterDates(){
        var outOfBoundsEvent = new WholeDayEvent("title", "desc", LocalDate.of(2021, 1, 2));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,2,16,0,0);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(outOfBoundsEvent, firstDateTime, secondDateTime);

        assertEquals(0, selectedAppointments.size());
    }

    //Tests when event starts before firstDateTime and ends between firstDateTime and secondDateTime.
    @Test
    public void EventStartsBeforeEndsBetween(){
        var event = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 15));
        firstDateTime = LocalDateTime.of(2020,1,15,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,31,16,0,0);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(event, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event starts between firstDateTime and secondDateTime and ends after secondDateTime.
    @Test
    public void EventStartsBetweenEndsAfter(){
        var event = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 15));
        firstDateTime = LocalDateTime.of(2020,1,1,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,15,16,0,0);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(event, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when event starts before firstDateTime and ends after secondDateTime.
    @Test
    public void EventStartsBeforeEndsAfter(){
        var event = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 15));
        firstDateTime = LocalDateTime.of(2020,1,15,12,0,0);
        secondDateTime = LocalDateTime.of(2020,1,15,16,0,0);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(event, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //* ***** REPEATED EVENTS TESTS *****

    //Tests when event starts and ends before firstDateTime but repeats (monthly) between first and second dateTime.
    @Test
    public void EventStartsBeforeRepeatsBetween(){
        var event = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 15));
        event.setFrequency(new FrequencyMonthly(null));

        firstDateTime = LocalDateTime.of(2020,1,31,12,0,0);
        secondDateTime = LocalDateTime.of(2020,3,16,16,0,0);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(event, firstDateTime, secondDateTime);

        assertEquals(2, selectedAppointments.size());
    }

    //Tests when event starts and repeats (daily) between first and second dateTime.
    @Test
    public void EventStartsAndRepeatsBetween(){
        var event = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 1));
        event.setFrequency(new FrequencyDaily(1, null));

        firstDateTime = LocalDateTime.of(2020,1,1,0,0,0);
        secondDateTime = LocalDateTime.of(2020,1,31,23,59,59);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(event, firstDateTime, secondDateTime);

        assertEquals(31, selectedAppointments.size());
    }

    //Tests when an event repetition (monthly) starts between first and second DateTime, but ends after.
    @Test
    public void RepetitionStartsBetweenEndsAfter(){
        var event = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 1));
        event.setFrequency(new FrequencyMonthly(null));

        firstDateTime = LocalDateTime.of(2020,2,1,0,0,0);
        secondDateTime = LocalDateTime.of(2020,2,1,13,59,59);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(event, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //Tests when an event repetition (yearly) starts before first DateTime but ends between first and second DateTime.
    @Test
    public void RepetitionStartsBeforeEndsBetween(){
        var event = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 1));
        event.setFrequency(new FrequencyAnnual(null));

        firstDateTime = LocalDateTime.of(2021,1,1,13,0,0);
        secondDateTime = LocalDateTime.of(2021,1,1,16,0,0);

        selectedAppointments = appointmentVisitor.visitWholeDayEvent(event, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }
}
