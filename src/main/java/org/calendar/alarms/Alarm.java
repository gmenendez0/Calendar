package org.calendar.alarms;

import java.time.LocalDateTime;

public abstract class Alarm {
    protected int id;
    protected LocalDateTime ringDateTime;

    public Alarm(int id, LocalDateTime ringDateTime){
        this.id = id;
        this.ringDateTime = ringDateTime;
    }

    //Post: Returns the id
    public int getId() {
        return id;
    }

    //Post: Rings the alarm. Returns true if it's called (for testing reasons).
    protected abstract void ring();

    //Post: Checks if it is time to ring, and in case it is, returns true. Otherwise, returns false.
    public void update(LocalDateTime nowTime){
        if(nowTime.equals(ringDateTime)) ring();
    }
}
