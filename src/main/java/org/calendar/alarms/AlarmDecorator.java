package org.calendar.alarms;

import java.time.LocalDateTime;

public abstract class AlarmDecorator extends Alarm {
    public Alarm alarm;

    public AlarmDecorator(int id, LocalDateTime ringDateTime, Alarm alarm){
        super(id, ringDateTime);
        this.alarm = alarm;
    }

    @Override
    public void ring() {
        alarm.ring();
    }
}
