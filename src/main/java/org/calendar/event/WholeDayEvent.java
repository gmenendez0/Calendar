package org.calendar.event;

import org.calendar.appointment.Appointment;
import org.calendar.visitor.Visitor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class WholeDayEvent extends Event {
    final static int ONE_DAY = 1;
    private LocalDate dateEvent;

    //Constructor.
    public WholeDayEvent(String title, String description, LocalDate dateEvent){
        super(title, description, dateEvent.atStartOfDay(), dateEvent.plusDays(ONE_DAY).atStartOfDay());
        this.dateEvent = dateEvent;
    }

    //Post: Sets the startDateTime of the event and consequently the endingDateTime.
    public void setStartDate(LocalDate startDate){
        this.startDateTime = startDate.atStartOfDay();
        this.endingDateTime = startDate.plusDays(ONE_DAY).atStartOfDay();
    }

    //@inheritDoc
    @Override
    public List<Appointment> acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        return visitor.visitWholeDayEvent(this, firstDayTime, secondDayTime);
    }

//    @Override
//    public Event nextEvent() {
//        LocalDateTime dateTimeEvent = this.getStartDateTime();
//        if (!this.thereIsNextEvent(dateTimeEvent)) return null;
//
//        String title = this.getTitle();
//        String description = this.getDescription();
//        LocalDate dateRepeat = this.getNextEventRegardDateTime(dateTimeEvent).toLocalDate();
//        Event eventRepeat = new WholeDayEvent(title, description, dateRepeat);
//        return eventRepeat;
//    }
}
