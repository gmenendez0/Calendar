package org.models.calendar.visitor.appointment_details;

import org.models.calendar.event.frequency.Frequency;

import java.time.LocalDateTime;

public class AppointmentDetailsVisitorImpl implements AppointmentDetailsVisitor{
    @Override
    public String detailsOfFrequencyEvent(Frequency frequency) {
        return frequency.toString();
    }

    @Override
    public String detailsOfDatesEvent(LocalDateTime start, LocalDateTime end){
        return "The Event starts on " + start + " and ends on " + end;
    }

    @Override
    public String detailsOfDatesTask(LocalDateTime expirationDateTime){
        return "The Expiration date of the alarm is " + expirationDateTime;
    }
}
