package org.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrecuencyMonthly extends Frecuency{
    private int interval;

    //Constructor.
    public FrecuencyMonthly(){
        this.interval = 1;
    }

    //Pre: receives the LocalDateTime.
    //Post: returns the next event date.
    @Override
    public LocalDateTime nextDate(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.interval, ChronoUnit.MONTHS);
        LocalDate day = dayTime.toLocalDate();
        if (super.getDeadline() != null){
            LocalDate lastDay = super.getDeadline();
            if (day.compareTo(lastDay) > 0){
                return null;
            }
        }
        return dayTime;
    }
}
