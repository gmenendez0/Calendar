package org.calendar.alarms;

public class SoundAlarm implements Alarm {
    @Override
    public void ring() {
        System.out.println("Sounding...");
    }
}
