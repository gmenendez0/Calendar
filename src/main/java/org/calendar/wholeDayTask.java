package org.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class wholeDayTask extends Task {
    final int ONE_DAY = 1;

    private LocalDate startDate;

    //Constructs a wholeDayTask
    public wholeDayTask(int id, String title, String description, LocalDate startDate, LocalDateTime expirationDateTime) {
        super(id, title, description, expirationDateTime);
        this.startDate = startDate;
    }

    //Post: Updates the startDate and the expirationDateTime.
    public void setStartDate(LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(ONE_DAY);
        this.setExpirationDateTime(endDate.atStartOfDay());

        this.startDate = startDate;
    }

    //Post: Returns startDate.
    public LocalDate getStartDate() {
        return this.startDate;
    }
}
