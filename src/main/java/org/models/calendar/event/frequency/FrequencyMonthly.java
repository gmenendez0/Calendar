package org.models.calendar.event.frequency;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrequencyMonthly extends Frequency {
    @JsonIgnore
    final int ONE_MONTH = 1;
    @JsonIgnore
    private int monthsToAdd;

    private final String subtype = "FrequencyMonthly";

    public FrequencyMonthly(LocalDate deadline){
        this.monthsToAdd = ONE_MONTH;
        this.deadline = deadline;
    }

    //Empty constructor for persistence (Jackson).
    public FrequencyMonthly(){}

    //@inheritDoc
    @Override
    public LocalDateTime nextRepetitionDateTime(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.monthsToAdd, ChronoUnit.MONTHS);
        LocalDate day = dayTime.toLocalDate();
        if (super.noNextEvent(day)) return null;
        if (dayTime.isEqual(date)) return null;

        return dayTime;
    }

    //@inheritDoc
    public String toString(){
        return "The frequency type is Monthly and your deadline is " + this.deadline.toString();
    }

}
