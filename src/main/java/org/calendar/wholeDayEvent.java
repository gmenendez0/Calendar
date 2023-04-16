package org.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class wholeDayEvent extends Event{
    private LocalDate startDate;

    public wholeDayEvent(int id, String title, String description,
                         LocalDate startDate){
        super(id, title, description, startDate.atStartOfDay(), startDate.atTime(23,59,59));
        this.startDate = startDate;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public LocalDate getStartDate(){
        return this.startDate;
    }

    public boolean isWholeDay(){return this.startDate != null;}
}
