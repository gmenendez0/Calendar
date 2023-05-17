package org.calendar.task;

import org.calendar.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Task extends Appointment {
    protected LocalDateTime expirationDateTime;

    public Task(String title, String description, LocalDateTime expirationDateTime) {
        super(title, description);
        this.expirationDateTime = expirationDateTime;
    }

    //Post: Returns expirationDateTime.
    public LocalDateTime getExpirationDateTime() {
        return this.expirationDateTime;
    }

    @Override
    public Map specificReport(){
        Map<String, Object> report = new HashMap<>();
        report.put("Type", "Task");
        specificSubType(report);
        report.put("Duration", this.expirationDateTime);
        report.put("Frequency", null);
        return report;
    }

    public abstract void specificSubType(Map<String, Object> report);
}
