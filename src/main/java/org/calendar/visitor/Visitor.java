package org.calendar.visitor;

import org.calendar.appointment.Appointment;
import org.calendar.event.PeriodTimeEvent;
import org.calendar.event.WholeDayEvent;
import org.calendar.task.ExpirationTimeTask;
import org.calendar.task.WholeDayTask;

import java.time.LocalDateTime;
import java.util.List;

public interface Visitor {
    //Pre: firstDateTime must be before secondDateTime.
    //Post:
    List<Appointment> visitPeriodTimeEvent(PeriodTimeEvent periodTimeEvent, LocalDateTime firstDateTime, LocalDateTime secondDateTime);

    //Pre: firstDateTime must be before secondDateTime.
    //Post:
    List<Appointment> visitWholeDayEvent(WholeDayEvent wholeDayEvent, LocalDateTime firstDateTime, LocalDateTime secondDateTime);

    //Pre: firstDateTime must be before secondDateTime.
    //Post:
    List<Appointment> visitWholeDayTask(WholeDayTask wholeDayTask, LocalDateTime firstDateTime, LocalDateTime secondDateTime);

    //Pre: firstDateTime must be before secondDateTime.
    //Post:
    List<Appointment> visitExpirationTimeTask(ExpirationTimeTask expirationTimeTask, LocalDateTime firstDateTime, LocalDateTime secondDateTime);
}
