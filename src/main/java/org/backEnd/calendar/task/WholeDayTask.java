package org.backEnd.calendar.task;

import org.backEnd.calendar.visitor.Visitor;
import org.backEnd.calendar.appointment.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class WholeDayTask extends Task {
    private String subtype = "WholeDayTask";

    @JsonIgnore
    final int ONE_DAY = 1;
    @JsonIgnore
    final int ENDING_HOUR = 23;
    @JsonIgnore
    final int ENDING_MINUTE = 59;
    @JsonIgnore
    final int ENDING_SECOND = 59;

    private LocalDateTime startDateTime;

    public WholeDayTask(String title, String description, LocalDate startDate) {
        super(title, description, null);
        this.startDateTime = startDate.atStartOfDay();
        expirationDateTime = startDate.atTime(ENDING_HOUR, ENDING_MINUTE, ENDING_SECOND);
    }

    //Empty constructor for persistence (Jackson).
    public WholeDayTask(){}

    //Post: getter needed for persistence.
    public String getSubtype(){
        return this.subtype;
    }

    //Post: setter needed for persistence.
    public void setSubtype(String subtype){
        this.subtype = subtype;
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
