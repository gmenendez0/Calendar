package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrequencyMonthly extends Frequency {
    final int ONE_MONTH = 1;
    private final int monthsToAdd;

    //Constructor.
    public FrequencyMonthly(){
        this.monthsToAdd = ONE_MONTH;
    }

    //@inheritDoc
    @Override
    public LocalDateTime nextEventDateTime(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.monthsToAdd, ChronoUnit.MONTHS);
        LocalDate day = dayTime.toLocalDate();

        if (super.noNextEvent(day)) return null;

        return dayTime;
    }
}
