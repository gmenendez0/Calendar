package org.models.calendar.alarms;

import java.time.LocalDateTime;

public class EmailAlarm extends Alarm {

    private final String subtype = "EmailAlarm";

    public EmailAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    //Empty constructor for persistence (Jackson).
    public EmailAlarm(){}

    //Post: Rings and sends an email to the user.
    @Override
    public void ring() {
        //Send mail algorithm...
    }

    public String toString(){
        return "Alarm Type: " + subtype + " and it will ring in " + this.ringDateTime.toString();
    }
}
