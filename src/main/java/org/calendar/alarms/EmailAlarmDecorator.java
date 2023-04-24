package org.calendar.alarms;

public class EmailAlarmDecorator extends AlarmDecorator {
    public EmailAlarmDecorator(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void ring(){
        System.out.println("Sending email...");
        if(alarm != null) alarm.ring();
    }
}
