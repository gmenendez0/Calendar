package org.calendar.alarms;

public class NotificationAlarm implements Alarm {
    @Override
    public void ring() {
        System.out.println("Sending notification...");
    }
}
