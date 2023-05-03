package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrequencyDaily extends Frequency {
    private final int daysToAdd;

    //Constructor.
    public FrequencyDaily(int interval){
        this.daysToAdd = interval;
    }

    //@inheritDoc
    @Override
    public LocalDateTime nextEventDateTime(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.daysToAdd, ChronoUnit.DAYS);
        LocalDate day = dayTime.toLocalDate();

        if (super.noNextEvent(day)) return null;

        return dayTime;
    }
}
