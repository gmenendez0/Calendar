package org.calendar.alarms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationAlarm extends Alarm {
    public NotificationAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    //Post: Rings and sends a notification to the user.
    @Override
    protected void ring() {
        //Send notif. algorithm...
    }

    @Override
    public List report(){
        List<Object> report = new ArrayList<>();
        report.add("NotificationAlarm");
        report.add(this.ringDateTime);
        return report;
    }
}
