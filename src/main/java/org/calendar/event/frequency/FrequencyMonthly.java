package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class FrequencyMonthly extends Frequency {
    final int ONE_MONTH = 1;
    private final int monthsToAdd;

    public FrequencyMonthly(LocalDate deadline){
        this.monthsToAdd = ONE_MONTH;
        this.deadline = deadline;
    }

    //@inheritDoc
    @Override
    public LocalDateTime nextRepetitionDateTime(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.monthsToAdd, ChronoUnit.MONTHS);
        LocalDate day = dayTime.toLocalDate();

        if (super.noNextEvent(day)) return null;

        return dayTime;
    }

    @Override
    public void subTypeFrequency(List<Object> report){
        report.add("FrequencyMonthly");
    }
}
