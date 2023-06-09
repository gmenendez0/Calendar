package org.models.calendar.event;

import org.models.calendar.event.frequency.Frequency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.models.calendar.appointment.Appointment;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.models.calendar.visitor.appointment_details.AppointmentDetailsVisitor;

import java.time.LocalDateTime;
import java.time.LocalTime;

//tags for json deserialization
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "subtype")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PeriodTimeEvent.class, name = "PeriodTimeEvent"),
        @JsonSubTypes.Type(value = WholeDayEvent.class, name = "WholeDayEvent")
})

public abstract class Event extends Appointment {
    protected LocalDateTime startDateTime;

    protected LocalDateTime endingDateTime;

    protected Frequency frequency;

    protected final String type = "Event";

    public Event(String title, String description, LocalDateTime startDateTime, LocalDateTime endingDateTime){
        super(title, description);
        this.startDateTime = startDateTime;
        this.endingDateTime = endingDateTime;
        frequency = null;
    }

    //Empty constructor for persistence (Jackson).
    public Event(){
        super();
    }

    //Post: getter needed for persistence.
    @Override
    public String getType(){
        return type;
    }

    @JsonIgnore
    //Post: Returns true if the event repeats, false otherwise.
    public boolean isRepeated(){
         return this.frequency != null;
    }

    @JsonIgnore
    //Post: Sets frequency to null.
    public void setNoRepeat(){
        this.frequency = null;
    }


    //Post: Returns the next date and time of the event with respect to the date and time received, or null if there is no repetition or no next event.
    private LocalDateTime getNextEventRegardDateTime(LocalDateTime date){
        if(isRepeated()) return frequency.nextRepetitionDateTime(date);
        return null;
    }

    //Post: Returns true if firstDateTime is after or equal to secondDateTime
    private boolean dateTimeIsAfterOrEqual(LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        return firstDateTime.isAfter(secondDateTime) || firstDateTime.isEqual(secondDateTime);
    }

    //Post: Returns the immediate next event s dateTime from the date passed, returns null if it does not repeat.
    private LocalDateTime findNextImmediateDate(LocalDateTime startOrEnding, LocalDateTime date){
        if (!this.isRepeated()) return null;
        while(dateTimeIsAfterOrEqual(date, startOrEnding)){
            startOrEnding = this.getNextEventRegardDateTime(startOrEnding);
            if (startOrEnding == null) return null;
        }
        
        return startOrEnding;
    }

    //Post: Returns the immediate next event s startDateTime from the date passed, returns null if it does not repeat.
    public LocalDateTime getNextRepetitionStartDateTime(LocalDateTime date) {
        return findNextImmediateDate(this.startDateTime, date);
    }

    //Post: Returns the immediate next event s endingDateTime from the date passed, returns null if it does not repeat.
    public LocalDateTime getNextRepetitionEndingDateTime(LocalDateTime date){
        return findNextImmediateDate(this.endingDateTime, date);
    }

    //post: Returns the start date time of the event.
    public LocalDateTime getStartDateTime(){
        return this.startDateTime;
    }


    //Post: Returns the ending date time of the event.
    public LocalDateTime getEndingDateTime(){
        return this.endingDateTime;
    }

    //Post: setter needed for persistence.
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    //Post: setter needed for persistence.
    public void setEndingDateTime(LocalDateTime endingDateTime) {
        this.endingDateTime = endingDateTime;
    }

    //@inheritDoc
    @Override
    public String acceptVisitorDetailsDates(AppointmentDetailsVisitor visitor) {
        return visitor.detailsOfDatesEvent(this.getStartDateTime(), this.endingDateTime);
    }

    //@inheritDoc
    @Override
    public String acceptVisitorDetailsFrequency(AppointmentDetailsVisitor visitor){
        if (!this.isRepeated()) return "This event does not repeat.";
        return visitor.detailsOfFrequencyEvent(frequency);
    }

    //Post: Returns true if there is an event after the received date, false otherwise.
    @JsonIgnore
    public boolean thereIsNextRepetition(LocalDateTime date){
        return this.frequency.hasNextRepetition(date);
    }

    //Post: Returns the last repeated event s endingDateTime. If repetition is infinite, it will return null.
    @JsonIgnore
    public LocalDateTime getLastRepetitionEndingDateTime(){
        LocalTime finalTime = this.endingDateTime.toLocalTime();
        return this.frequency.getDeadlineDateTime(finalTime);
    }

    //Post: returns from the next repetition of the current event.
    @JsonIgnore
    public abstract Event getNextRepetition();

    //Post: getter needed for persistence.
    public abstract Frequency getFrequency();

    //Post: setter needed for persistence.
    public abstract void setFrequency(Frequency frequency);

}