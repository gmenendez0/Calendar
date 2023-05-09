package org.calendar.event;

import org.calendar.appointment.Appointment;
import org.calendar.event.frequency.Frequency;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

    //Post: Returns the next date and time of the event with respect to the date and time received, or null if there is no repetition or no next event.
    public LocalDateTime getNextEventRegardDateTime(LocalDateTime date){
        if(isRepeated()) return frequency.nextEventDateTime(date);
        return null;
    }

    //Pre: Receive any random LocalDateTime.
    //Post: Returns the next date with the time the event starts, returns null if it does not repeat.
    public LocalDateTime getNextStartDateTime(LocalDateTime date){
        if (!this.isRepeated()) return null;
        LocalDateTime start = this.startDateTime;
        while(date.isAfter(start) || date.isEqual(start)){
            start = this.getNextEventRegardDateTime(start);
        }
        return start;
    }

    //Pre: Receive any random LocalDateTime.
    //Post: Returns the next date with the time the event ends, returns null if it does not repeat.
    public LocalDateTime getNextEndDateTime(LocalDateTime date){
        if (!this.isRepeated()) return null;
        LocalDateTime end = this.endingDateTime;
        while(date.isAfter(end) || date.isEqual(end)){
            end = this.getNextEventRegardDateTime(end);
        }
        return end;
    }

    //post: Returns the start date time of the event.
    public LocalDateTime getStartDateTime(){
        return this.startDateTime;
    }

    //post: Returns the ending date time of the event.
    public LocalDateTime getEndingDateTime(){
        return this.endingDateTime;
    }

    //Post: returns true if after the received date there is an event, false if no event.
    public boolean thereIsNextEvent(LocalDateTime date){
        return this.frequency.hasNextDate(date);
    }

    //Post: returns the last LocalDateTime that the event will have, if it is infinite, it will return null.
    public LocalDateTime getLastRepetitionEndingDateTime(){
        LocalTime finalTime = this.endingDateTime.toLocalTime();
        return this.frequency.getDeadlineDateTime(finalTime);
    }

//    public abstract Event nextEvent();

}