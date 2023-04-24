package org.calendar.alarms;

import java.time.LocalDateTime;

public class NotificationAlarmDecorator extends AlarmDecorator {

    public NotificationAlarmDecorator(int id, LocalDateTime ringDateTime, Alarm alarm){
        super(id, ringDateTime, alarm);
    }

    @Override
    public void ring(){
        System.out.println("Sending notification...");
        if(alarm != null) alarm.ring();
    }

    @Override
    public void update() {
        if(LocalDateTime.now().equals(ringDateTime)) ring();
    }
}
