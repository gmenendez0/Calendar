package org.calendar;

import java.time.LocalDateTime;

public class Task extends Appointment {
    private LocalDateTime expirationDateTime;

    //Constructs a task
    public Task(int id, String title, String description, LocalDateTime expirationDateTime) {
        super(id, title, description);
        this.expirationDateTime = expirationDateTime;
    }

    //Post: Updates the expirationDateTime.
    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    //Post: Returns expirationDateTime.
    public LocalDateTime getExpirationDateTime() {
        return this.expirationDateTime;
    }
}
