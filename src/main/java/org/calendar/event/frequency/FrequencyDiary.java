package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrequencyDiary extends Frequency {
    private final int daysToAdd;

    //Constructor.
    public FrequencyDiary(int interval){
        this.daysToAdd = interval;
    }

    //Post: returns the next event s date.
    @Override
    public LocalDateTime nextEventDateTime(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.daysToAdd, ChronoUnit.DAYS);
        LocalDate day = dayTime.toLocalDate();

        if (noNextEvent(day)) return null;

        return dayTime;
    }
}
