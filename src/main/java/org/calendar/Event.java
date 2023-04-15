package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event extends Appointment{
    private Duration duration;
    private Frecuency frecuency;

    //Constructor: if the event has a duration other than the whole day.
    public Event(int id, String title, String description,
                 LocalDateTime startEvent, LocalDateTime endingEvent){
        super(id, title, description);
        this.duration = new Duration(startEvent, endingEvent);
    }

    //Constructor: if the event lasts a whole day.
    public Event(int id, String title, String description,
                 LocalDate eventDate){
        super(id, title, description);
        this.duration = new Duration(eventDate);
    }

    //Post: Return the isRepeated.
    public boolean eventIsRepeated(){
        return this.frecuency != null;
    }

    //Post: changes the frequency state of the event, to not repeat.
    public void noRepeat(){
        this.frecuency = null;
    }

    //Post: adds the frequency to the event, to monthly or annual frequency.
    public void repeatEventDiary(int interval){
        this.frecuency = new FrecuencyDiary(interval);
    }

    //Post: adds the frequency to the event, to daily frequency.
    public void repeatEventAnnual(){
        this.frecuency = new FrecuencyAnnual();
    }

    public void repeatEventMonthly(){
        this.frecuency = new FrecuencyMonthly();
    }

    //Post: adds the frequency to the event, to weekly frequency.
    public void repeatEventWeeklyWithInterval(ArrayList<DayOfWeek> weekDays){
        this.frecuency = new FrecuencyWeekly(weekDays);
    }

    public void repeatEventWeekly(){
        this.frecuency = new FrecuencyWeekly();
    }

    public LocalDateTime nextEventDate(LocalDateTime date){
        if(this.eventIsRepeated()){
            return this.frecuency.nextDate(date);
        }
        return null;
    }

    public LocalDateTime whenTheEventStart(){
        return this.duration.whenItStarts();
    }

    public LocalDateTime whenTheEventEnd(){
        return this.duration.whenItEnds();
    }

    //Pre: receives the dates and times of the new duration.
    //Post:  the duration
    public void changeEventDuration(LocalDateTime startDateEventTime, LocalDateTime endingDateEventTime){
        this.duration.changeDuration(startDateEventTime, endingDateEventTime);
    }

    //Pre: receives the day the event takes place.
    //Post: change the duration
    public void changeEventDuration(LocalDate dateEvent){
        this.duration.changeDuration(dateEvent);
    }
}
