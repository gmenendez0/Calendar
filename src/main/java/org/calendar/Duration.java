package org.calendar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
public class Duration {
    private boolean wholeDay;
    private LocalDateTime startDateTime;
    private LocalDateTime endingDateTime;

    //Constructs a task with wholeDay = false;
    public Duration(boolean wholeDay, LocalDateTime startDateTime, LocalDateTime endingDateTime){
        this.wholeDay = wholeDay;
        this.startDateTime = startDateTime;
        this.endingDateTime = endingDateTime;
    }

    //la hora final del events sera a las 23:59:59 del misdo dia.
    //Constructs a task with wholeDay = true;
    public Duration(boolean wholeDay, LocalDate eventDate){
        this.wholeDay = wholeDay;
        this.startDateTime = eventDate.atStartOfDay();
        final LocalTime EndingTime = LocalTime.of(23,59,59);
        this.endingDateTime = eventDate.atTime(EndingTime);
    }

    //Post: Returns wholeDay;
    public boolean isWholeDay(){
        return this.wholeDay;
    }

    //Post: Returns the startDateTime;
    public LocalDateTime startEvent(){
        return this.startDateTime;
    }

    //Post: Updates the wholeDay;
    public void setWholeDay(boolean wholeDay){
        this.wholeDay = wholeDay;
    }

    //Post: Updates the startDateTime;
    public void setStartDateTime(LocalDateTime startDateTime){
        this.startDateTime = startDateTime;
    }

    //Post: Updates the endingDateTime;
    public void setEndingDateTime(LocalDateTime endingDateTime) {
        this.endingDateTime = endingDateTime;
    }
}
