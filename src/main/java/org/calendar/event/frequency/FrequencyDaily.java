package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class FrequencyDaily extends Frequency {

    private String subtype = "FrequencyDaily";
    private int daysToAdd;

    public FrequencyDaily(int interval, LocalDate deadline){
        this.daysToAdd = interval;
        this.deadline = deadline;
    }

    public FrequencyDaily(){}

    public String getSubtype(){
        return subtype;
    }

    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    public int getDaysToAdd(){
        return daysToAdd;
    }

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

    @Override
    public void subTypeFrequency(List<Object> report){
        report.add("FrequencyDaily");
        report.add(this.daysToAdd);
    }
}
