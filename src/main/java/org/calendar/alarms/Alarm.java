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

    //Post: Rings the alarm.
    protected abstract void ring();

    //Post: Checks if it is time to ring.
    public void update(){
        if(LocalDateTime.now().equals(ringDateTime)) ring();
    }
}
