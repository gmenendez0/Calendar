package org.calendar.task;

import org.calendar.appointment.Appointment;
import org.calendar.visitor.Visitor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class WholeDayTask extends Task {
    final int ONE_DAY = 1;
    final int ENDING_HOUR = 23;
    final int ENDING_MINUTE = 59;
    final int ENDING_SECOND = 59;

    private LocalDateTime startDateTime;

    //Constructs a WholeDayTask
    public WholeDayTask(String title, String description, LocalDate startDate) {
        super(title, description, null);
        this.startDateTime = startDate.atStartOfDay();
        expirationDateTime = startDate.atTime(ENDING_HOUR, ENDING_MINUTE, ENDING_SECOND);
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

    //@inheritDoc
    @Override
    public List<Appointment> acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        return visitor.visitWholeDayTask(this, firstDayTime, secondDayTime);
    }
}
