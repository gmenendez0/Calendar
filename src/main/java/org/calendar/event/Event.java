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

    //Pre: ???
    //Post: ???
    private LocalDateTime modularizable(LocalDateTime startOrEnding, LocalDateTime date){
        while(date.isAfter(startOrEnding) || date.isEqual(startOrEnding)){
            startOrEnding = this.getNextEventRegardDateTime(startOrEnding);
        }

        return startOrEnding;
    }

    //Pre: Receive any LocalDateTime.
    //Post: Returns the immediate next event s startDateTime, returns null if it does not repeat.
    public LocalDateTime getNextStartDateTime(LocalDateTime date){
        if (!this.isRepeated()) return null;

        LocalDateTime start = this.startDateTime;
        while(date.isAfter(start) || date.isEqual(start)){
            start = this.getNextEventRegardDateTime(start);
        }

        //start = modularizable(start, date);

        return start;
    }

    //Pre: Receive any LocalDateTime.
    //Post: Returns the immediate next event s endingDateTime, returns null if it does not repeat.
    public LocalDateTime getNextEndingDateTime(LocalDateTime date){
        if (!this.isRepeated()) return null;

        LocalDateTime end = this.endingDateTime;
        while(date.isAfter(end) || date.isEqual(end)){
            end = this.getNextEventRegardDateTime(end);
        }

        //end = modularizable(end, date);

        return end;
    }

    //post: Returns the start date time of the event.
    public LocalDateTime getStartDateTime(){
        return this.startDateTime;
    }

    //Post: Returns the ending date time of the event.
    public LocalDateTime getEndingDateTime(){
        return this.endingDateTime;
    }

    //Post: Returns true if there is an event after the received date, false otherwise.
    public boolean thereIsNextEvent(LocalDateTime date){
        return this.frequency.hasNextDate(date);
    }

    //Post: Returns the last repeated event s endingDateTime. If repetition is infinite, it will return null.
    public LocalDateTime getLastRepetitionEndingDateTime(){
        LocalTime finalTime = this.endingDateTime.toLocalTime();
        return this.frequency.getDeadlineDateTime(finalTime);
    }

//    public abstract Event nextEvent();

}