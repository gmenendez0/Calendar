package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrequencyAnnual extends Frequency {
    final int ONE_YEAR = 1;


    private final int yearsToAdd;

    //Constructor.
    public FrequencyAnnual(){
        this.yearsToAdd = ONE_YEAR;
    }

    //Post: returns the next event date.
    @Override
    public LocalDateTime nextEventDateTime(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.yearsToAdd, ChronoUnit.YEARS);
        LocalDate day = dayTime.toLocalDate();

        if (noNextEvent(day)) return null;

        return date.plus(this.yearsToAdd, ChronoUnit.YEARS);
    }
}
