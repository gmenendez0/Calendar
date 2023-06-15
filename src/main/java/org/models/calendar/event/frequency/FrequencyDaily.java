package org.models.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrequencyDaily extends Frequency {
    private final String subtype = "FrequencyDaily";
    private int daysToAdd;

    public FrequencyDaily(int interval, LocalDate deadline){
        this.daysToAdd = interval;
        this.deadline = deadline;
    }

    //Empty constructor for persistence (Jackson).
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
        if (dayTime.isEqual(date) && this.daysToAdd != 0) return null;
        return dayTime;
    }

    //@inheritDoc
    public String toString(){
        return "The frequency type is Daily and your deadline is " + this.deadline.toString() + ".\n Repeats every " + this.getDaysToAdd() + " days.";
    }

}
