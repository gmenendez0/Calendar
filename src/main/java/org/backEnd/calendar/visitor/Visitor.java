package org.backEnd.calendar.visitor;

import org.backEnd.calendar.event.WholeDayEvent;
import org.backEnd.calendar.appointment.Appointment;
import org.backEnd.calendar.event.PeriodTimeEvent;
import org.backEnd.calendar.task.ExpirationTimeTask;
import org.backEnd.calendar.task.WholeDayTask;

import java.time.LocalDateTime;
import java.util.List;

public interface Visitor {
    //Pre: firstDateTime must be before secondDateTime.
    //Post: Returns a list with all the tasks derived from the received task that take place between the received dates.
    List<Appointment> visitPeriodTimeEvent(PeriodTimeEvent periodTimeEvent, LocalDateTime firstDateTime, LocalDateTime secondDateTime);

    //Pre: firstDateTime must be before secondDateTime.
    //Post: Returns a list with all the tasks derived from the received task that take place between the received dates.
    List<Appointment> visitWholeDayEvent(WholeDayEvent wholeDayEvent, LocalDateTime firstDateTime, LocalDateTime secondDateTime);

    //Pre: firstDateTime must be before secondDateTime.
    //Post: Returns a list with all the events derived from the received event that take place between the received dates.
    List<Appointment> visitWholeDayTask(WholeDayTask wholeDayTask, LocalDateTime firstDateTime, LocalDateTime secondDateTime);

    //Pre: firstDateTime must be before secondDateTime.
    //Post: Returns a list with all the events derived from the received event that take place between the received dates.
    List<Appointment> visitExpirationTimeTask(ExpirationTimeTask expirationTimeTask, LocalDateTime firstDateTime, LocalDateTime secondDateTime);
}
