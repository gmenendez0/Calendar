package org.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrecuencyAnnual extends Frecuency{
    private int interval;

    //Constructor.
    public FrecuencyAnnual(){
        this.interval = 1;
    }

    //Pre: receives the LocalDateTime.
    //Post: returns the next event date.
    @Override
    public LocalDateTime nextDate(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.interval, ChronoUnit.YEARS);
        LocalDate day = dayTime.toLocalDate();
        if (super.hasADeadline()){
            if (super.hasExceededDeadline(day)){
                return null;
            }
        }
        return date.plus(this.interval, ChronoUnit.YEARS);
    }
}
