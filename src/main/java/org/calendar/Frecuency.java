package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Frecuency {

    private LocalDate deadline;
    //Post: returns the deadline.
    public LocalDate getDeadline(){
        return this.deadline;
    }

    //Pre: receive a date and now what will be the deadline.
    public void setDeadline(LocalDate date){
        this.deadline = date;
    }

    public abstract LocalDateTime nextDate(LocalDateTime date);
}