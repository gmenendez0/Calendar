package org.calendar.event;

import org.calendar.appointment.Appointment;
import org.calendar.event.frequency.Frequency;

import java.time.LocalDateTime;

public abstract class Event extends Appointment {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endingDateTime;
    private Frequency Frequency;

    //Constructor
    public Event(String title, String description, LocalDateTime startDateTime, LocalDateTime endingDateTime){
        super(title, description);
        this.startDateTime = startDateTime;
        this.endingDateTime = endingDateTime;
    }

    //Post: Return the true if the event repeats, false otherwise.
    public boolean IsRepeated(){
         return this.Frequency != null;
    }

    //Post: changes the frequency state of the event, to not repeat.
    public void setNoRepeat(){
        this.Frequency = null;
    }

    //Pre: receives the frequency that the event will have.
    //Post: Changes the event s frequency.
    public void setEventFrequency(Frequency frequency){
        this.Frequency = frequency;
    }

    //Pre: receive a date to know your next event s date.
    //Post: If the event repeats, it returns the next repetition dateTime. Otherwise, it returns null
    public LocalDateTime nextEventDateTime(LocalDateTime date){
        if(this.IsRepeated()) return this.Frequency.nextEventDateTime(date);

        return null;
    }

    //post: Returns the start date time of the event
    public LocalDateTime getStartDateTime(){
        return this.startDateTime;
    }

    //post: Returns the ending date time of the event
    public LocalDateTime getEndingDateTime(){
        return this.endingDateTime;
    }
}
