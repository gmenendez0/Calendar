package org.calendar.task;

import org.calendar.appointment.Appointment;
import org.calendar.visitor.Visitor;

import java.time.LocalDateTime;
import java.util.List;

public class ExpirationTimeTask extends Task {
    public ExpirationTimeTask(String title, String description, LocalDateTime expirationDateTime) {
        super(title, description, expirationDateTime);
    }

    //Post: Sets the expirationDateTime.
    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }


    //@inheritDoc
    @Override
    public List<Appointment> acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        return visitor.visitExpirationTimeTask(this, firstDayTime, secondDayTime);
    }
}
