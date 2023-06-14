package org.models.calendar.visitor.detailsVisitor;

import org.models.calendar.event.frequency.Frequency;

import java.time.LocalDateTime;

public interface detailsVisitor {

    String detailsOfFrequencyEvent(Frequency frequency);

    String detailsOfDatesEvent(LocalDateTime start, LocalDateTime end);
    String detailsOfDatesTask(LocalDateTime expirationDateTime);

}
