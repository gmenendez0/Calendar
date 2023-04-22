package org.calendar.event;

import java.time.LocalDateTime;

public class PeriodTimeEvent extends Event {
    public PeriodTimeEvent(int id, String title, String description, LocalDateTime startDateTime, LocalDateTime endingDateTime) {
        super(id, title, description, startDateTime, endingDateTime);
    }

    //Post: changes the startDateTime of the event.
    public void setEventStartDateTime(LocalDateTime EventStartDateTime){
        this.startDateTime = EventStartDateTime;
    }

    //Post: changes the endingDateTime of the event.
    public void setEventEndingDateTime(LocalDateTime EventEndingDateTime){
        this.endingDateTime = EventEndingDateTime;
    }
}
