package org.calendar.alarms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmailAlarm extends Alarm {
    public EmailAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    //Post: Rings and sends an email to the user.
    @Override
    protected void ring() {
        //Send mail algorithm...
    }

    @Override
    public List report(){
        List<Object> report = new ArrayList<>();
        report.add(this.id);
        report.add("EmailAlarm");
        report.add(this.ringDateTime);
        return report;
    }
}
