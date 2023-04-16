package org.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrecuencyDiary extends Frecuency{
    private int interval;
    public FrecuencyDiary(int interval){
        this.interval = interval;
    }
    @Override
    public LocalDateTime nextDate(LocalDateTime date){
        LocalDate day = date.toLocalDate();
        if (super.getDeadline() != null){
            LocalDate lastDay = super.getDeadline();
            if (day.compareTo(lastDay) >= 0){
                return null;
            }
        }

        return date.plus(this.interval, ChronoUnit.DAYS);
    }
}
