package org.calendar;

import java.time.LocalDateTime;

public class Event extends Appointment{

    private LocalDateTime startDateTime;
    private LocalDateTime endingDateTime;
    private Frecuency frecuency;

    //Constructor: if the event has a duration other than the whole day.
    public Event(int id, String title, String description,
                 LocalDateTime startDateTime, LocalDateTime endingDateTime){
        super(id, title, description);
        this.startDateTime = startDateTime;
        this.endingDateTime = endingDateTime;
    }

    //Post: Return the isRepeated.
    public boolean eventIsRepeated(){
        return this.frecuency != null;
    }

    //Post: changes the frequency state of the event, to not repeat.
    public void noRepeat(){
        this.frecuency = null;
    }

    //Pre: receives the frequency that the event will have.
    //Post: adds the frequency to the event.
    public void repeatEvent(Frecuency frecuency){
        this.frecuency = frecuency;
    }

    //Pre: receive a date to know your next event.
    //Post: returns the following date in case it has repetitions.
    public LocalDateTime nextEventDate(LocalDateTime date){
        if(this.eventIsRepeated()){
            return this.frecuency.nextDate(date);
        }
        return null;
    }

    public LocalDateTime whenTheEventStart(){
        return this.startDateTime;
    }

    public LocalDateTime whenTheEventEnd(){
        return this.endingDateTime;
    }

    //Pre: receives the dates and times of the new start dateTime.}
    //Post: change the startDateEventTime.
    public void changeEventStart(LocalDateTime startDateEventTime){
        this.startDateTime = startDateEventTime;
    }

    //Pre: receives the dates and times of the new end dateTime.
    //Post: change the endingDateTime.
    public void changeEventEnd(LocalDateTime endingDateTime){
        this.endingDateTime = endingDateTime;
    }
}
