package org.calendar.task;

import org.calendar.appointment.Appointment;

import java.time.LocalDateTime;

public abstract class Task extends Appointment {
    protected LocalDateTime expirationDateTime;

    //Constructs a task
    public Task(String title, String description, LocalDateTime expirationDateTime) {
        super(title, description);
        this.expirationDateTime = expirationDateTime;
    }

    //Post: Returns expirationDateTime.
    public LocalDateTime getExpirationDateTime() {
        return this.expirationDateTime;
    }
}
