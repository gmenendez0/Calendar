package org.calendar.alarms;

import java.time.LocalDateTime;

public class SoundAlarmDecorator extends AlarmDecorator {
    public SoundAlarmDecorator(int id, LocalDateTime ringDateTime, Alarm alarm){
        super(id, ringDateTime, alarm);
    }

    @Override
    public void ring(){
        System.out.println("Sounding...");
        if(alarm != null) alarm.ring();
    }

    @Override
    public void update() {
        if(LocalDateTime.now().equals(ringDateTime)) ring();
    }
}
