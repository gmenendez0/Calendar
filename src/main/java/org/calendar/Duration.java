package org.calendar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Duration {
    private LocalDateTime startDateTime;
    private LocalDateTime endingDateTime;

    //Constructs a task with wholeDay = false;
    //Pre: the 'startDateTime' must be before the 'endingDateTime';
    public Duration(LocalDateTime startDateTime, LocalDateTime endingDateTime){
        this.startDateTime = startDateTime;
        this.endingDateTime = endingDateTime;
    }

    //Constructs a task with wholeDay = true;
    public Duration(LocalDate date){
        this.startDateTime = date.atStartOfDay();
        final LocalTime EndingTime = LocalTime.of(23,59,59);
        this.endingDateTime = date.atTime(EndingTime);
    }

    //Post: Returns the startDateTime;
    public LocalDateTime whenItStarts(){
        return this.startDateTime;
    }

    //Post: Returns the endingDateTime;
    public LocalDateTime whenItEnds(){
        return this.endingDateTime;
    }

    //Pre: method receives the date and time of how long the object should last.
    //Post: update the wholeDay, the startDateTime and the endingDateTime.
    public void changeDuration(LocalDateTime startDateTime, LocalDateTime endingDateTime){
        this.startDateTime = startDateTime;
        this.endingDateTime = endingDateTime;
    }

    //Pre: method receives the date when the object occurs.
    //Post: update the wholeDay, the startDateTime and the endingDateTime.
    public void changeDuration(LocalDate date){
        this.startDateTime = date.atStartOfDay();
        final LocalTime EndingTime = LocalTime.of(23,59,59);
        this.endingDateTime = date.atTime(EndingTime);
    }


}
