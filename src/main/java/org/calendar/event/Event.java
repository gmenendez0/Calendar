package org.calendar.event;

import org.calendar.appointment.Appointment;
import org.calendar.event.frequency.Frequency;

import java.time.LocalDateTime;

public abstract class Event extends Appointment {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endingDateTime;
    private Frequency frequency;

    //Constructor
    public Event(String title, String description, LocalDateTime startDateTime, LocalDateTime endingDateTime){
        super(title, description);
        this.startDateTime = startDateTime;
        this.endingDateTime = endingDateTime;
    }

    //Post: Returns true if the event repeats, false otherwise.
    public boolean isRepeated(){
         return this.frequency != null;
    }

    //Post: Sets frequency to null.
    public void setNoRepeat(){
        this.frequency = null;
    }

    //Post: Sets the event's frequency.
    public void setEventFrequency(Frequency frequency){
        this.frequency = frequency;
    }

    //Post: Returns the date of following repetition event from the received date, or null if there is no repetition or no next event.
    public LocalDateTime getNextEventDateTime(LocalDateTime date){
        if(isRepeated()) return frequency.nextEventDateTime(date);

        return null;
    }

    //post: Returns the start date time of the event.
    public LocalDateTime getStartDateTime(){
        return this.startDateTime;
    }

    //post: Returns the ending date time of the event.
    public LocalDateTime getEndingDateTime(){
        return this.endingDateTime;
    }
}