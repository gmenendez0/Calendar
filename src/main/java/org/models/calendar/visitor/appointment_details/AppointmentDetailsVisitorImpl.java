package org.models.calendar.visitor.appointment_details;

import org.models.calendar.event.frequency.Frequency;

import java.time.LocalDateTime;

public class AppointmentDetailsVisitorImpl implements AppointmentDetailsVisitor{
    //Post: return the frequency details in string format.
    //@inheritDoc
    @Override
    public String detailsOfFrequencyEvent(Frequency frequency) {
        return frequency.toString();
    }

    //@inheritDoc
    @Override
    public String detailsOfDatesEvent(LocalDateTime start, LocalDateTime end){
        return "The event starts on " + start + " and ends on " + end + ".";
    }

    //@inheritDoc
    @Override
    public String detailsOfDatesTask(LocalDateTime expirationDateTime){
        return "The task expires on " + expirationDateTime + ".";
    }
}
