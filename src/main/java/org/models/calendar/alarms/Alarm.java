package org.models.calendar.alarms;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "subtype")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailAlarm.class, name = "EmailAlarm"),
        @JsonSubTypes.Type(value = NotificationAlarm.class, name = "NotificationAlarm"),
        @JsonSubTypes.Type(value = SoundAlarm.class, name = "SoundAlarm")
})

public abstract class Alarm {
    protected int id;
    protected LocalDateTime ringDateTime;

    public Alarm(int id, LocalDateTime ringDateTime){
        this.id = id;
        this.ringDateTime = ringDateTime;
    }

    //Empty constructor for persistence (Jackson).
    public Alarm(){}

    //Post: Returns the id
    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public LocalDateTime getRingDateTime() {
        return ringDateTime;
    }

    public void setRingDateTime(LocalDateTime ringDateTime){
        this.ringDateTime = ringDateTime;
    }

    //Post: Rings the alarm. Returns true if it's called (for testing reasons).
    public abstract void ring();

    //Post: Checks if it is time to ring. If it is time to ring, rings the alarm.
    public void update(LocalDateTime nowTime){
        if(nowTime.equals(ringDateTime)) ring();
    }

}
