package org.calendar.alarms;

import java.time.LocalDateTime;

public class NotificationAlarmDecorator extends AlarmDecorator {
    public NotificationAlarmDecorator(int id, LocalDateTime ringDateTime, Alarm alarm){
        super(id, ringDateTime, alarm);
    }

    //Post: Makes the "alarm" attribute ring and also sends a notification to the user.
    @Override
    protected boolean ring(){
        if(alarm != null) return alarm.ring();
        return true;
    }
}
