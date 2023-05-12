package org.calendar.event;

import org.calendar.appointment.Appointment;
import org.calendar.visitor.Visitor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class WholeDayEvent extends Event {
    final static int ONE_DAY = 1;
    final int ENDING_HOUR = 23;
    final int ENDING_MINUTE = 59;
    final int ENDING_SECOND = 59;
    private LocalDate eventDate;

    //Constructor.
    public WholeDayEvent(String title, String description, LocalDate dateEvent){
        super(title, description, dateEvent.atStartOfDay(), dateEvent.plusDays(ONE_DAY).atStartOfDay());
        this.eventDate = dateEvent;
    }

    //Post: Sets the startDateTime of the event and consequently the endingDateTime.
    public void setStartDate(LocalDate startDate){
        this.startDateTime = startDate.atStartOfDay();
        this.endingDateTime = startDate.atTime(ENDING_HOUR, ENDING_MINUTE, ENDING_SECOND);
    }

    //@inheritDoc
    @Override
    public List<Appointment> acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        return visitor.visitWholeDayEvent(this, firstDayTime, secondDayTime);
    }

    //Post: returns from the next event of the current event.
    @Override
    public Event getNextRepetition() {
        LocalDateTime dateTimeEvent = this.getStartDateTime();
        if (!this.thereIsNextRepetition(dateTimeEvent)) return null;

        String title = this.getTitle();
        String description = this.getDescription();
        LocalDate updatedDate = this.getNextEventRegardDateTime(dateTimeEvent).toLocalDate();

        var updatedEvent = new WholeDayEvent(title, description, updatedDate);
        //! Hay que hacer que updatedEvent tenga la misma frequency que el evento actual.
        return updatedEvent;
    }
}
