package org.calendar.visitor;

import org.calendar.event.PeriodTimeEvent;
import org.calendar.event.WholeDayEvent;
import org.calendar.task.ExpirationTimeTask;
import org.calendar.task.WholeDayTask;

import java.time.LocalDateTime;

public interface Visitor {
    //Pre: firstDateTime must be before secondDateTime.
    //Post:
    void visitPeriodTimeEvent(PeriodTimeEvent periodTimeEvent, LocalDateTime firstDateTime, LocalDateTime secondDateTime);

    //Pre: firstDateTime must be before secondDateTime.
    //Post:
    void visitWholeDayEvent(WholeDayEvent wholeDayEvent, LocalDateTime firstDateTime, LocalDateTime secondDateTime);

    //Pre: firstDateTime must be before secondDateTime.
    //Post:
    void visitWholeDayTask(WholeDayTask wholeDayTask, LocalDateTime firstDateTime, LocalDateTime secondDateTime);

    //Pre: firstDateTime must be before secondDateTime.
    //Post:
    void visitExpirationTimeTask(ExpirationTimeTask expirationTimeTask, LocalDateTime firstDateTime, LocalDateTime secondDateTime);
}
