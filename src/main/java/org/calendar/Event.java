package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event extends Appointment{

    private boolean isRepeated;
    private Duration duration;
    private Frecuency frecuency;

    //Constructor: if the event has a duration other than the whole day.
    public Event(int id, String title, String description,
                 LocalDateTime startEvent, LocalDateTime endingEvent){
        super(id, title, description);
        this.isRepeated = false;
        this.duration = new Duration(startEvent, endingEvent);
    }

    //Constructor: if the event lasts a whole day.
    public Event(int id, String title, String description,
                 LocalDate eventDate){
        super(id, title, description);
        this.isRepeated = false;
        this.duration = new Duration(eventDate);
    }

    //Post: Return the isRepeated.
    public boolean eventIsRepeated(){
        return this.isRepeated;
    }

    //Post: changes the frequency state of the event, to not repeat.
    public void noRepeat(){
        this.isRepeated = false;
        this.frecuency = null;
    }

    //Post: adds the frequency to the event, to monthly or annual frequency.
    public void repeatEvent(char frecuencyType){
        this.isRepeated = true;
        this.frecuency = new Frecuency(frecuencyType);
    }

    //Post: adds the frequency to the event, to daily frequency.
    public void repeatEvent(char frecuencyType, int interval){
        this.isRepeated = true;
        this.frecuency = new Frecuency(frecuencyType, interval);
    }

    //Post: adds the frequency to the event, to weekly frequency.
    public void repeatEvent(char frecuencyType, ArrayList<DayOfWeek> weekDays){
        this.isRepeated = true;
        this.frecuency = new Frecuency(frecuencyType, weekDays);
    }

    //Post: changes the frequency state of the event, to monthly or annual frequency.
    public void changeEventReplays(char frecuencyType){
        this.frecuency.changeFrecuency(frecuencyType);
    }

    //Post: changes the frequency state of the event, to daily frequency.
    public void changeEventReplays(char frecuencyType, int interval){
        this.frecuency.changeFrecuency(frecuencyType, interval);
    }

    //Post: changes the frequency state of the event, to weekly frequency.
    public void changeEventReplays(char frecuencyType, ArrayList<DayOfWeek> weekDays){
        this.frecuency.changeFrecuency(frecuencyType, weekDays);
    }

    //Pre: receives a date and time of the event.
    //Post: returns the next date of the event.
    public LocalDateTime nextEventDate(LocalDateTime date){
        return this.frecuency.nextDate(date);
    }

    //Pre: receives the dates and times of the new duration.
    //Post: change the duration
    public void changeEventDuration(LocalDateTime startDateEventTime, LocalDateTime endingDateEventTime){
        this.duration.changeDuration(startDateEventTime, endingDateEventTime);
    }

    //Pre: receives the day the event takes place.
    //Post: change the duration
    public void changeEventDuration(LocalDate dateEvent){
        this.duration.changeDuration(dateEvent);
    }
}
