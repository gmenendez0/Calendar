package org.calendar.alarms;

import java.time.LocalDateTime;

public class SoundAlarm extends Alarm {
    public SoundAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    @Override
    public void ring() {
        System.out.println("Sounding...");
    }

    @Override
    public void update() {
        if(LocalDateTime.now().equals(ringDateTime)) ring();
    }
}
