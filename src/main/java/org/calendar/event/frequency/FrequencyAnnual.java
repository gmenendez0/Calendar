package org.calendar.event.frequency;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrequencyAnnual extends Frequency{

    private final String subtype = "FrequencyAnnual";
    @JsonIgnore
    final int ONE_YEAR = 1;
    @JsonIgnore
    private int yearsToAdd;

    public FrequencyAnnual(LocalDate deadline){
        this.yearsToAdd = ONE_YEAR;
        this.deadline = deadline;
    }

    //Empty constructor for persistence (Jackson).
    public FrequencyAnnual(){}

    //@inheritDoc
    @Override
    public LocalDateTime nextRepetitionDateTime(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.yearsToAdd, ChronoUnit.YEARS);
        LocalDate day = dayTime.toLocalDate();

        if (super.noNextEvent(day)) return null;
        if (dayTime.isEqual(date)) return null;

        return date.plus(this.yearsToAdd, ChronoUnit.YEARS);
    }

}
