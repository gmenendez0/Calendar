package org.calendar.event;

import org.calendar.alarms.Alarm;
import org.calendar.appointment.Appointment;
import org.calendar.visitor.Visitor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //Post: returns from the next event of the current event.
    @Override
    public Event getNextRepetition() {
        LocalDateTime dateTimeStartEvent = getStartDateTime();
        if (!thereIsNextRepetition(dateTimeStartEvent)) return null;

        String title = getTitle();
        String description = getDescription();
        LocalDateTime dateTimeStartRepeat = getNextRepetitionStartDateTime(dateTimeStartEvent);
        LocalDateTime dateTimeEndRepeat = getNextRepetitionEndingDateTime(getEndingDateTime());

        var repeatedEvent = new PeriodTimeEvent(title, description, dateTimeStartRepeat, dateTimeEndRepeat);
        repeatedEvent.setEventFrequency(this.getFrequency());

        return repeatedEvent;
    }

    @Override
    public void specificSubType(Map<String, Object> hash){
        hash.put("SubType", "PeriodTimeEvent");
    }
}
