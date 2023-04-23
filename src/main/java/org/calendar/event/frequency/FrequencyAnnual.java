package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrequencyAnnual extends Frequency {
    final int ONE_YEAR = 1;


    private final int interval;

    //Constructor.
    public FrequencyAnnual(){
        this.interval = ONE_YEAR;
    }

    //Pre: receives the LocalDateTime.
    //Post: returns the next event date.
    @Override
    public LocalDateTime nextEventDateTime(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.interval, ChronoUnit.YEARS);
        LocalDate day = dayTime.toLocalDate();

        if (noNextEvent(day)) return null;

        return date.plus(this.interval, ChronoUnit.YEARS);
    }
}
