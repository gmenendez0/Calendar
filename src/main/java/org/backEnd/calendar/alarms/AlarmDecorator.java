package org.backEnd.calendar.alarms;

import java.time.LocalDateTime;

public abstract class AlarmDecorator extends Alarm {
    protected Alarm alarm;

    public AlarmDecorator(int id, LocalDateTime ringDateTime, Alarm alarm){
        super(id, ringDateTime);
        this.alarm = alarm;
    }

    public AlarmDecorator(){}

    //@inheritDoc
    @Override
    public void ring() {
        alarm.ring();
    }
}
