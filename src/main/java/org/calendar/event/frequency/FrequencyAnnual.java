package org.calendar.event.frequency;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FrequencyAnnual extends Frequency{

    private String subtype = "FrequencyAnnual";
    @JsonIgnore
    final int ONE_YEAR = 1;
    @JsonIgnore
    private int yearsToAdd;

    public FrequencyAnnual(LocalDate deadline){
        this.yearsToAdd = ONE_YEAR;
        this.deadline = deadline;
    }

    public FrequencyAnnual(){}

    public String getSubtype(){
        return subtype;
    }

    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //@inheritDoc
    @Override
    public LocalDateTime nextRepetitionDateTime(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.yearsToAdd, ChronoUnit.YEARS);
        LocalDate day = dayTime.toLocalDate();

        if (super.noNextEvent(day)) return null;

        return date.plus(this.yearsToAdd, ChronoUnit.YEARS);
    }

    @Override
    public void subTypeFrequency(List<Object> report){
        report.add("FrequencyAnnual");
    }
}
