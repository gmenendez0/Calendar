package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class FrequencyDaily extends Frequency {
    private final int daysToAdd;

    public FrequencyDaily(int interval, LocalDate deadline){
        this.daysToAdd = interval;
        this.deadline = deadline;
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
