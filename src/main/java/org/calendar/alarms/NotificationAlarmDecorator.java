package org.calendar.alarms;

import java.time.LocalDateTime;

public class NotificationAlarmDecorator extends AlarmDecorator {
    public NotificationAlarmDecorator(int id, LocalDateTime ringDateTime, Alarm alarm){
        super(id, ringDateTime, alarm);
    }

    //Post: Makes the "alarm" attribute ring and also sends a notification to the user.
    @Override
    protected void ring(){
        System.out.println("Sending notification...");
        if(alarm != null) alarm.ring();
    }
}
