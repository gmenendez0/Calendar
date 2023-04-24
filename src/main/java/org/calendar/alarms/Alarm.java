package org.calendar.alarms;

import org.observable_subject.Observer;

import java.time.LocalDateTime;

public abstract class Alarm implements Observer {
    protected int id;
    protected LocalDateTime ringDateTime;

    public Alarm(int id, LocalDateTime ringDateTime){
        this.id = id;
        this.ringDateTime = ringDateTime;
    }

    public abstract void ring();
}
