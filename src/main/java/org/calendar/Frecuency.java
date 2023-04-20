package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Frecuency {

    private LocalDate deadline;

    public boolean hasExceededDeadline(LocalDate date){
        return date.compareTo(this.deadline) > 0;
    }

    //Post: returns true if there is a deadline. If not, return false.
    public boolean hasADeadline(){
        return this.deadline != null;
    }

    //Pre: receive the date that will be the deadline.
    //Post: adds the deadline to the event.
    public void addDeadline(LocalDate date){
        this.deadline = date;
    }

    //Pre: receives the number of repetitions of the event.
    //Post: adds the deadline to the event, counting the number of repetitions.
    public void addDeadlineWithRepetitions(int repetitions, LocalDateTime date){
        for(int i = 0; i < repetitions; i++){
            date = this.nextDate(date);
        }
        this.addDeadline(date.toLocalDate());
    }

    //Pre: receive a date and time what will be the deadline.
    public abstract LocalDateTime nextDate(LocalDateTime date);

}