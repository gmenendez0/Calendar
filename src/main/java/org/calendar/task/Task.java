package org.calendar.task;

import org.calendar.Appointment;

import java.time.LocalDateTime;

public abstract class Task extends Appointment {
    protected LocalDateTime expirationDateTime;

    //Constructs a task
    public Task(int id, String title, String description, LocalDateTime expirationDateTime) {
        super(id, title, description);
        this.expirationDateTime = expirationDateTime;
    }

    //Post: Returns expirationDateTime.
    public LocalDateTime getExpirationDateTime() {
        return this.expirationDateTime;
    }
}
