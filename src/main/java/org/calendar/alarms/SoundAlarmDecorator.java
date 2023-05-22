package org.calendar.alarms;

import java.time.LocalDateTime;

public class SoundAlarmDecorator extends AlarmDecorator {
    public SoundAlarmDecorator(int id, LocalDateTime ringDateTime, Alarm alarm){
        super(id, ringDateTime, alarm);
    }

    public SoundAlarmDecorator(){}

    //Post: Rings making a sound and makes the "alarm" attribute ring.
    @Override
    public void ring(){
        if(alarm != null) alarm.ring();
        //Make sound algorithm...
    }
}
