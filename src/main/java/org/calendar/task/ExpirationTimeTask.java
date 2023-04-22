package org.calendar.task;

import java.time.LocalDateTime;

public class ExpirationTimeTask extends Task {
    public ExpirationTimeTask(int id, String title, String description, LocalDateTime expirationDateTime) {
        super(id, title, description, expirationDateTime);
    }

    //Post: Sets the expirationDateTime.
    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }
}
