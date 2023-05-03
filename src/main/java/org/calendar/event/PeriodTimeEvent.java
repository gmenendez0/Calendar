package org.calendar.event;

import org.calendar.visitor.Visitor;

import java.time.LocalDateTime;

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
    public void acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        visitor.visitPeriodTimeEvent(this, firstDayTime, secondDayTime);
    }
}
