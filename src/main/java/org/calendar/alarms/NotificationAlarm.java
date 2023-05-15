package org.calendar.alarms;

import java.time.LocalDateTime;

public class NotificationAlarm extends Alarm {
    public NotificationAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    //Post: Rings and sends a notification to the user.
    @Override
    protected void ring() {
        //Send notif. algorithm...
    }
}
