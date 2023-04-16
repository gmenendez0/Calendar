package org.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrecuencyAnnual extends Frecuency{
    private int interval;

    public FrecuencyAnnual(){
        this.interval = 1;
    }
    @Override
    public LocalDateTime nextDate(LocalDateTime date){
        LocalDate day = date.plus(this.interval, ChronoUnit.YEARS).toLocalDate();
        if (super.getDeadline() != null){
            LocalDate lastDay = super.getDeadline();
            if (day.compareTo(lastDay) >= 0){
                return null;
            }
        }
        return date.plus(this.interval, ChronoUnit.YEARS);
    }
}
