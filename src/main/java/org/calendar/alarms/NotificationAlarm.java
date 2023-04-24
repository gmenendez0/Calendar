package org.calendar.alarms;

import java.time.LocalDateTime;

public class NotificationAlarm extends Alarm {
    public NotificationAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    @Override
    public void ring() {
        System.out.println("Sending notification...");
    }

    @Override
    public void update() {
        if(LocalDateTime.now().equals(ringDateTime)) ring();
    }
}
