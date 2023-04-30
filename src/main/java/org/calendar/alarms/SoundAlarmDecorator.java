package org.calendar.alarms;

import java.time.LocalDateTime;

public class SoundAlarmDecorator extends AlarmDecorator {
    public SoundAlarmDecorator(int id, LocalDateTime ringDateTime, Alarm alarm){
        super(id, ringDateTime, alarm);
    }

    //Post: Rings making a sound and makes the "alarm" attribute ring.
    @Override
    protected boolean ring(){
        if(alarm != null) return alarm.ring();
        return true;
    }
}
