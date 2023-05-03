package org.calendar.event;

import org.calendar.visitor.Visitor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    //Post: Accepts a visitor and returns the "visit" return value.
    @Override
    public void acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        visitor.visitWholeDayEvent(this, firstDayTime, secondDayTime);
    }
}
