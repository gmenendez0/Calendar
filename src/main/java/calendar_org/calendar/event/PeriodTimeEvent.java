package calendar_org.calendar.event;

import calendar_org.calendar.appointment.Appointment;
import calendar_org.calendar.event.frequency.Frequency;
import calendar_org.calendar.visitor.Visitor;

import java.time.LocalDateTime;
import java.util.List;

public class PeriodTimeEvent extends Event {
    private String subtype = "PeriodTimeEvent";

    public PeriodTimeEvent(String title, String description, LocalDateTime startDateTime, LocalDateTime endingDateTime) {
        super(title, description, startDateTime, endingDateTime);
    }

    public PeriodTimeEvent(){}

    //Post: getter needed for persistence.
    public String getSubtype(){
        return this.subtype;
    }

    //Post: setter needed for persistence.
    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //Post: Sets the startDateTime of the event.
    public void setEventStartDateTime(LocalDateTime EventStartDateTime){
        this.startDateTime = EventStartDateTime;
    }

    //Post: Sets the endingDateTime of the event.
    public void setEventEndingDateTime(LocalDateTime EventEndingDateTime){
        this.endingDateTime = EventEndingDateTime;
    }

    //Post: Sets the event's frequency.
    public void setFrequency(Frequency frequency){
        this.frequency = frequency;
    }

    //Post: Returns event's frequency.
    public Frequency getFrequency(){
        return this.frequency;
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
        repeatedEvent.setFrequency(this.getFrequency());

        return repeatedEvent;
    }
}
