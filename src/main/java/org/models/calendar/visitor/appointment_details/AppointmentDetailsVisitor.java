package org.models.calendar.visitor.appointment_details;

import org.models.calendar.event.frequency.Frequency;

import java.time.LocalDateTime;

public interface AppointmentDetailsVisitor{
    //Post: return the frequency details in string format.
    String detailsOfFrequencyEvent(Frequency frequency);

    //Post: returns the start and end of the event in a string.
    String detailsOfDatesEvent(LocalDateTime start, LocalDateTime end);

    //Post: returns the expiration date of the task in a string.
    String detailsOfDatesTask(LocalDateTime expirationDateTime);

}
