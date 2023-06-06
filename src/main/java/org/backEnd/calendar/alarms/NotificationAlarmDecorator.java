package org.backEnd.calendar.alarms;

import java.time.LocalDateTime;

public class NotificationAlarmDecorator extends AlarmDecorator {
    public NotificationAlarmDecorator(int id, LocalDateTime ringDateTime, Alarm alarm){
        super(id, ringDateTime, alarm);
    }

    //Empty constructor for persistence (Jackson).
    public NotificationAlarmDecorator(){}

    //Post: Makes the "alarm" attribute ring and also sends a notification to the user.
    @Override
    public void ring(){
        if(alarm != null) alarm.ring();
        //Send notif. algorithm...
    }
}
