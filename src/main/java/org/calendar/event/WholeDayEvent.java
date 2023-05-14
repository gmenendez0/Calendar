package org.calendar.event;

import org.calendar.appointment.Appointment;
import org.calendar.visitor.Visitor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class WholeDayEvent extends Event {
    final static int ENDING_HOUR = 23;
    final static int ENDING_MINUTE = 59;
    final static int ENDING_SECOND = 59;
    private LocalDate eventDate;

    //Constructor.
    public WholeDayEvent(String title, String description, LocalDate dateEvent){
        super(title, description, dateEvent.atStartOfDay(), dateEvent.atTime(ENDING_HOUR,ENDING_MINUTE, ENDING_SECOND));
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

    //Post: Returns the immediate next repetition from the current event or Null if event is not repeated.
    @Override
    public Event getNextRepetition() {
        LocalDateTime dateTimeEvent = getStartDateTime();
        if (!thereIsNextRepetition(dateTimeEvent)) return null;

        String title = getTitle();
        String description = getDescription();
        LocalDate dateRepeat = getNextEventRegardDateTime(dateTimeEvent).toLocalDate();

        var repeatedEvent = new WholeDayEvent(title, description, dateRepeat);
        repeatedEvent.setEventFrequency(this.getFrequency());

        return repeatedEvent;
    }
}
