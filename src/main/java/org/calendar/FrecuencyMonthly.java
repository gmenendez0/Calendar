package org.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrecuencyMonthly extends Frecuency{
    private int interval;

    public FrecuencyMonthly(){
        this.interval = 1;
    }

    @Override
    public LocalDateTime nextDate(LocalDateTime date){
        LocalDate day = date.plus(this.interval, ChronoUnit.MONTHS).toLocalDate();
        if (super.getDeadline() != null){
            LocalDate lastDay = super.getDeadline();
            if (day.compareTo(lastDay) >= 0){
                return null;
            }
        }
        return date.plus(this.interval, ChronoUnit.MONTHS);
    }
}
