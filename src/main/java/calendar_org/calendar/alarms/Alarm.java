package calendar_org.calendar.alarms;

import calendar_org.calendar.event.Event;
import calendar_org.calendar.event.PeriodTimeEvent;
import calendar_org.calendar.event.WholeDayEvent;
import calendar_org.calendar.task.Task;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "subtype")
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

    //Post: Checks if it is time to ring, and in case it is, returns true. Otherwise, returns false.
    public void update(LocalDateTime nowTime){
        if(nowTime.equals(ringDateTime)) ring();
    }

}
