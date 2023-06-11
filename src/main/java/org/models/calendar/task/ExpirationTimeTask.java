package org.models.calendar.task;

import org.models.calendar.alarms.Alarm;
import org.models.calendar.visitor.Visitor;
import org.models.calendar.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpirationTimeTask extends Task {

    private String subtype = "ExpirationTimeTask";

    public ExpirationTimeTask(String title, String description, LocalDateTime expirationDateTime) {
        super(title, description, expirationDateTime);
    }

    //Empty constructor for persistence (Jackson).
    public ExpirationTimeTask(){}

    //Post: getter needed for persistence.
    public String getSubtype(){
        return this.subtype;
    }

    //Post: setter needed for persistence.
    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //Post: Sets the expirationDateTime.
    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    //@inheritDoc
    @Override
    public List<Appointment> acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        return visitor.visitExpirationTimeTask(this, firstDayTime, secondDayTime);
    }

    //@inheritDoc
    //Format example: "Title   Task expiration date-time: 1/1/23 12:12:23   Completed: Yes"
    @Override
    public String formatToString(){
        String isCompleted = "No";
        if(isCompleted()) isCompleted = "Yes";

        return this.getTitle() + "   " + "\n Task expiration date-time: " + this.getExpirationDateTime().toString() + "\n Completed: " + isCompleted;
    }

    @Override
    public Map<String, String> dataToMapOfString(){

        Map<String, String> hashData = new HashMap<>();

        hashData.put("Title", this.getTitle());
        hashData.put("Description", this.getDescription());
        hashData.put("Type", this.getType());
        hashData.put("DateImportant", "Expiration Date: " + this.getExpirationDateTime().toString());
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
