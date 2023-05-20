package calendar_org.calendar.visitor;

import calendar_org.calendar.appointment.Appointment;
import calendar_org.calendar.event.PeriodTimeEvent;
import calendar_org.calendar.event.WholeDayEvent;
import calendar_org.calendar.task.ExpirationTimeTask;
import calendar_org.calendar.task.WholeDayTask;

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
