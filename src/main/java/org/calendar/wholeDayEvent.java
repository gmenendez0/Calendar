package org.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class wholeDayEvent extends Event{
    private LocalDate startDate;

    //Constructor.
    public wholeDayEvent(int id, String title, String description,
                         LocalDate startDate){
        super(id, title, description, startDate.atStartOfDay(), startDate.atTime(23,59,59));
        this.startDate = startDate;
    }

    //Pre: receives the LocalDate.
    //Post: change the startDate.
    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    //Post: return the startDate.
    public LocalDate getStartDate(){
        return this.startDate;
    }

    //Post: returns true or false depending on whether it is whole day.
    public boolean isWholeDay(){return this.startDate != null;}
}
