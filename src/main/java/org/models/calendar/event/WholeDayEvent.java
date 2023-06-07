package org.models.calendar.event;

import org.models.calendar.appointment.Appointment;
import org.models.calendar.event.frequency.Frequency;
import org.models.calendar.visitor.Visitor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class WholeDayEvent extends Event {
    private String subtype = "WholeDayEvent";
    @JsonIgnore
    final static int ENDING_HOUR = 23;
    @JsonIgnore
    final static int ENDING_MINUTE = 59;
    @JsonIgnore
    final static int ENDING_SECOND = 59;

    private LocalDate eventDate;

    public WholeDayEvent(String title, String description, LocalDate dateEvent){
        super(title, description, dateEvent.atStartOfDay(), dateEvent.atTime(ENDING_HOUR,ENDING_MINUTE, ENDING_SECOND));
        this.eventDate = dateEvent;
    }

    //Empty constructor for persistence (Jackson).
    public WholeDayEvent(){}

    //Post: getter needed for persistence.
    public String getSubtype(){
        return this.subtype;
    }

    //Post: setter needed for persistence.
    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //Post: Sets the startDateTime of the event and consequently the endingDateTime.
    public void setStartDate(LocalDate startDate){
        this.startDateTime = startDate.atStartOfDay();
        this.endingDateTime = startDate.atTime(ENDING_HOUR, ENDING_MINUTE, ENDING_SECOND);
    }

    //Post: Sets the event's frequency.
    public void setFrequency(Frequency frequency){
        this.frequency = frequency;
    }

    //Post: Returns event s frequency.
    public Frequency getFrequency(){
        return this.frequency;
    }

    //@inheritDoc
    @Override
    public List<Appointment> acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        return visitor.visitWholeDayEvent(this, firstDayTime, secondDayTime);
    }

    //Post: Returns the immediate next repetition from the current event or Null if event is not repeated.
    @Override
    public Event getNextRepetition() {
        LocalDateTime dateTimeEvent = getStartDateTime();
        if (!thereIsNextRepetition(dateTimeEvent)) return null;

        String title = getTitle();
        String description = getDescription();
        LocalDate dateRepeat = getNextRepetitionStartDateTime(dateTimeEvent).toLocalDate();

        var repeatedEvent = new WholeDayEvent(title, description, dateRepeat);
        repeatedEvent.setFrequency(this.getFrequency());

        return repeatedEvent;
    }

    //@inheritDoc
    //Format example: "Title   Event date: 1/1/23   Completed: Yes"
    @Override
    public String formatToString(){
        String isCompleted = "No";
        if(isCompleted()) isCompleted = "Yes";

        return this.getTitle() + "   " + "Event date: " + this.eventDate.toString() + "   Completed: " + isCompleted;
    }
}
