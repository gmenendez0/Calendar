package org.calendar.alarms;

public class EmailAlarm implements Alarm {
    @Override
    public void ring() {
        System.out.println("Sending mail...");
    }
}
