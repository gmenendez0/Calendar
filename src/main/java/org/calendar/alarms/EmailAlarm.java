package org.calendar.alarms;

import java.time.LocalDateTime;

public class EmailAlarm extends Alarm {
    public EmailAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    @Override
    public void ring() {
        System.out.println("Sending mail...");
    }

    @Override
    public void update() {
        if(LocalDateTime.now().equals(ringDateTime)) ring();
    }
}
