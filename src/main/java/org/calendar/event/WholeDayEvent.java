package org.calendar.event;

import java.time.LocalDate;

public class WholeDayEvent extends Event {
    final static int ONE_DAY = 1;

    //Constructor.
    public WholeDayEvent(String title, String description, LocalDate startDate){
        super(title, description, startDate.atStartOfDay(), startDate.plusDays(ONE_DAY).atStartOfDay());
    }

    //Post: Sets the startDateTime of the event and consequently the endingDateTime.
    public void setStartDate(LocalDate startDate){
        this.startDateTime = startDate.atStartOfDay();
        this.endingDateTime = startDate.plusDays(ONE_DAY).atStartOfDay();
    }
}
