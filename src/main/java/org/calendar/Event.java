package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event extends Appointment{

    private LocalDateTime startDateTime;
    private LocalDateTime endingDateTime;
    private Frecuency frecuency;

    private LocalDate searchDeadline(int repetitions){
        LocalDateTime date = this.whenTheEventStart();
        LocalDate next = null;
        for(int i = 1; i <= repetitions; i++){
            date = this.nextEventDate(date);
            if (i == repetitions){
                next = date.toLocalDate();
            }
        }
        return next;
    }

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

    public void repeatEventWeekly(ArrayList<DayOfWeek> weekDay){
        this.frecuency = new FrecuencyWeekly(weekDay);
    }

    public void addDeadline(LocalDate date){
        this.frecuency.setDeadline(date);
    }

    public void addDeadline(int repetitions){
        LocalDate date = this.searchDeadline(repetitions);
        this.frecuency.setDeadline(date);
    }

    //Post: devuelve la fecha del sig evento repetido.
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

    //Pre: receives the dates and times of the new duration.
    //Post:  the duration
    public void changeEventStart(LocalDateTime startDateEventTime){
        this.startDateTime = startDateEventTime;
    }

    public void changeEventEnd(LocalDateTime endingDateTime){
        this.endingDateTime = endingDateTime;
    }
}
