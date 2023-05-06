package org.calendar.event;

import org.calendar.appointment.Appointment;
import org.calendar.visitor.Visitor;

import java.time.LocalDateTime;
import java.util.List;

public class PeriodTimeEvent extends Event {
    public PeriodTimeEvent(String title, String description, LocalDateTime startDateTime, LocalDateTime endingDateTime) {
        super(title, description, startDateTime, endingDateTime);
    }

    //Post: Sets the startDateTime of the event.
    public void setEventStartDateTime(LocalDateTime EventStartDateTime){
        this.startDateTime = EventStartDateTime;
    }

    //Post: Sets the endingDateTime of the event.
    public void setEventEndingDateTime(LocalDateTime EventEndingDateTime){
        this.endingDateTime = EventEndingDateTime;
    }

    //@inheritDoc
    @Override
    public List<Appointment> acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        return visitor.visitPeriodTimeEvent(this, firstDayTime, secondDayTime);
    }
}
