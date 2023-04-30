package org.calendar.alarms;

import java.time.LocalDateTime;

public abstract class AlarmDecorator extends Alarm {
    protected final Alarm alarm;

    public AlarmDecorator(int id, LocalDateTime ringDateTime, Alarm alarm){
        super(id, ringDateTime);
        this.alarm = alarm;
    }

    @Override
    protected boolean ring() {
        return alarm.ring();
    }
}
