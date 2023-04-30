package org.calendar.alarms;

import java.time.LocalDateTime;

public class SoundAlarm extends Alarm {
    public SoundAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    //Post: Rings and makes a sound.
    @Override
    protected boolean ring() {
        return true;
    }
}
