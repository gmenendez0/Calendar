package org.calendar.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WholeDayTask extends Task {
    final int ONE_DAY = 1;

    private LocalDateTime startDateTime;

    //Constructs a WholeDayTask
    public WholeDayTask(String title, String description, LocalDate startDate) {
        super(title, description, null);
        this.startDateTime = startDate.atStartOfDay();
        expirationDateTime = startDateTime.plusDays(ONE_DAY);
    }

    //Post: Updates the startDate and, consequentially, the expirationDateTime.
    public void setStartDate(LocalDate startDate) {
        this.startDateTime = startDate.atStartOfDay();

        LocalDate endDate = startDate.plusDays(ONE_DAY);
        this.expirationDateTime = endDate.atStartOfDay();
    }

    //Post: Returns startDateTime of the event.
    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }
}
