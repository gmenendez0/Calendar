package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event extends Appointment{

    private LocalDateTime startDateTime;
    private LocalDateTime endingDateTime;
    private Frecuency frecuency;

    //Post: receives an integer representing the occurrences of the event.
    //Pre: return the deadline.
    private LocalDate searchDeadline(int repetitions){
        LocalDateTime date = this.whenTheEventStart();
        for(int i = 0; i < repetitions; i++){
            date = this.nextEventDate(date);
        }
        return date.toLocalDate();
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

    //Post: adds the frequency to the event, to monthly frequency.
    public void repeatEventMonthly(){
        this.frecuency = new FrecuencyMonthly();
    }

    //Pre: receives the array of days in which the events take place
    //Post: adds the frequency to the event, to weekly frequency.
    public void repeatEventWeekly(ArrayList<DayOfWeek> weekDay){
        this.frecuency = new FrecuencyWeekly(weekDay);
    }

    //Pre: receive the date that will be the deadline.
    //Post: adds the deadline to the event.
    public void addDeadline(LocalDate date){
        this.frecuency.setDeadline(date);
    }

    //Pre: receives the number of repetitions of the event.
    //Post: adds the deadline to the event, counting the number of repetitions.
    public void addDeadline(int repetitions){
        LocalDate date = this.searchDeadline(repetitions);
        this.frecuency.setDeadline(date);
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
