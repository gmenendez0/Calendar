package org.calendar.alarms;

public class SoundAlarmDecorator extends AlarmDecorator {
    public SoundAlarmDecorator(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void ring(){
        System.out.println("Sounding...");
        if(alarm != null) alarm.ring();
    }
}
