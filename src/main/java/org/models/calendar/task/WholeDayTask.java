package org.models.calendar.task;

import org.models.calendar.alarms.Alarm;
import org.models.calendar.visitor.Visitor;
import org.models.calendar.appointment.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    //Post: Returns startDateTime of the event.
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    //@inheritDoc
    @Override
    public List<Appointment> acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        return visitor.visitWholeDayTask(this, firstDayTime, secondDayTime);
    }

    //@inheritDoc
    //Format example: "Title   Task date: 1/1/23   Completed: Yes"
    @Override
    public String formatToString(){
        String isCompleted = "No";
        if(isCompleted()) isCompleted = "Yes";

        return this.getTitle() + "   " + "\n Task date: " + this.getStartDateTime().toLocalDate().toString() + "\n Completed: " + isCompleted;
    }

    @Override
    public Map<String, String> dataToMapOfString(){

        Map<String, String> hashData = new HashMap<>();

        hashData.put("Title", this.getTitle());
        hashData.put("Description", this.getDescription());
        hashData.put("Type", this.getType());
        hashData.put("DateImportant", "Initial Date: " + this.getStartDateTime().toString() + "\nExpiration Date: " + this.getExpirationDateTime().toString());
        if (this.withAlarms()){
            String listAlarms = "";
            for (Alarm alarm : this.getAlarms()) {
                listAlarms += alarm.toString() + "\n";
            }
            hashData.put("Alarms", listAlarms);
        } else {
            hashData.put("Alarms", "This task has no alarms.");
        }

        return hashData;
    }
}
