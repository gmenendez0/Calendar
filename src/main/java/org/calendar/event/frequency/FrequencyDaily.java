package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrequencyDaily extends Frequency {
    private String subtype = "FrequencyDaily";
    private int daysToAdd;

    public FrequencyDaily(int interval, LocalDate deadline){
        this.daysToAdd = interval;
        this.deadline = deadline;
    }

    public FrequencyDaily(){}

    //Post: getter needed for persistence.
    public int getDaysToAdd(){
        return daysToAdd;
    }

    //Post: setter needed for persistence.
    public void setDaysToAdd(int daysToAdd){
        this.daysToAdd = daysToAdd;
    }

    @Override
    public LocalDateTime nextRepetitionDateTime(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.daysToAdd, ChronoUnit.DAYS);
        LocalDate day = dayTime.toLocalDate();

        if (super.noNextEvent(day)) return null;

        return dayTime;
    }

}
