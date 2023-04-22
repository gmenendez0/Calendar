package org.alarms;

import java.time.LocalDateTime;

public class Alarm {
    private int id;
    private int targetId;
    private LocalDateTime ringDateTime;

    //Constructor
    public Alarm(int id, int targetId, LocalDateTime ringDateTime){
        this.id = id;
        this.targetId = targetId;
        this.ringDateTime = ringDateTime;
    }

    //Change the date and time of alarm.
    public void setRingDateTime(LocalDateTime ringDateTime){
        this.ringDateTime = ringDateTime;
    }
}
