package org.calendar.task;

import org.calendar.visitor.Visitor;

import java.time.LocalDateTime;

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
    public void acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        visitor.visitExpirationTimeTask(this, firstDayTime, secondDayTime);
    }
}
