package org.calendar.alarms;

public class NotificationAlarmDecorator extends AlarmDecorator {
    public NotificationAlarmDecorator(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void ring(){
        System.out.println("Sending notification...");
        if(alarm != null) alarm.ring();
    }
}
