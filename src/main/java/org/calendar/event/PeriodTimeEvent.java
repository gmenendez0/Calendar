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

    //Post: Returns the immediate next repetition from the current event or Null if event is not repeated.
    @Override
    public Event getNextRepetition() {
        LocalDateTime dateTimeStartEvent = this.getStartDateTime();
        LocalDateTime dateTimeEndEvent = this.getEndingDateTime();
        if (!this.thereIsNextRepetition(dateTimeStartEvent)) return null;

        String title = this.getTitle();
        String description = this.getDescription();
        LocalDateTime dateTimeStartUpdated = this.getNextEventRegardDateTime(dateTimeStartEvent);
        LocalDateTime dateTimeEndUpdated = this.getNextEventRegardDateTime(dateTimeEndEvent);

        var updatedEvent = new PeriodTimeEvent(title, description, dateTimeStartUpdated, dateTimeEndUpdated);
        //! Hay que hacer que updatedEvent tenga la misma frequency que el evento actual.
        return updatedEvent;
    }
}
