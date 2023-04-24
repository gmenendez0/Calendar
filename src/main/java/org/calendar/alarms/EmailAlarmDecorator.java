package org.calendar.alarms;

import java.time.LocalDateTime;

public class EmailAlarmDecorator extends AlarmDecorator {
    public EmailAlarmDecorator(int id, LocalDateTime ringDateTime, Alarm alarm){
        super(id, ringDateTime, alarm);
    }

    @Override
    public void ring(){
        System.out.println("Sending email...");
        if(alarm != null) alarm.ring();
    }

    @Override
    public void update() {
        if(LocalDateTime.now().equals(ringDateTime)) ring();
    }
}
