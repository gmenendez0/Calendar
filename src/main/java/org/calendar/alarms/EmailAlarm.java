package org.calendar.alarms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmailAlarm extends Alarm {

    private String subtype = "EmailAlarm";

    public EmailAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    public EmailAlarm(){}

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //Post: Rings and sends an email to the user.
    @Override
    protected void ring() {
        //Send mail algorithm...
    }
}
