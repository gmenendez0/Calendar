package org.models.calendar.visitor.appointment_details;

import org.models.calendar.event.frequency.Frequency;

import java.time.LocalDateTime;

public class AppointmentDetailsVisitorImpl implements AppointmentDetailsVisitor{

    //Post: return the frequency details in string format.
    @Override
    public String detailsOfFrequencyEvent(Frequency frequency) {
        return frequency.toString();
    }

    //Post: returns the start and end of the event in a string.
    @Override
    public String detailsOfDatesEvent(LocalDateTime start, LocalDateTime end){
        return "The Event starts on " + start + " and ends on " + end;
    }

    //Post: returns the expiration date of the task in a string.
    @Override
    public String detailsOfDatesTask(LocalDateTime expirationDateTime){
        return "The Expiration date of the alarm is " + expirationDateTime;
    }
}
