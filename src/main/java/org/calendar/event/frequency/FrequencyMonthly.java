package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrequencyMonthly extends Frequency {
    final int ONE_MONTH = 1;
    private final int interval;

    //Constructor.
    public FrequencyMonthly(){
        this.interval = ONE_MONTH;
    }

    //Pre: receives the LocalDateTime.
    //Post: returns the next event date, null if there is no next event.
    @Override
    public LocalDateTime nextEventDateTime(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.interval, ChronoUnit.MONTHS);
        LocalDate day = dayTime.toLocalDate();

        if (noNextEvent(day)) return null;

        return dayTime;
    }
}
