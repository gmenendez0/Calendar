package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FrequencyAnnual extends Frequency{
    final int ONE_YEAR = 1;
    private final int yearsToAdd;

    public FrequencyAnnual(LocalDate deadline){
        this.yearsToAdd = ONE_YEAR;
        this.deadline = deadline;
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
