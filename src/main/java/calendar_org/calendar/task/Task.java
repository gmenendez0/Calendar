package calendar_org.calendar.task;

import calendar_org.calendar.appointment.Appointment;
import calendar_org.calendar.event.Event;
import calendar_org.calendar.event.PeriodTimeEvent;
import calendar_org.calendar.event.WholeDayEvent;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;

//tags for json deserialization
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "subtype")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ExpirationTimeTask.class, name = "ExpirationTimeTask"),
        @JsonSubTypes.Type(value = WholeDayTask.class, name = "WholeDayTask")
})

public abstract class Task extends Appointment {
    protected LocalDateTime expirationDateTime;
    private String type = "Task";

    public Task(String title, String description, LocalDateTime expirationDateTime) {
        super(title, description);
        this.expirationDateTime = expirationDateTime;
    }

    public Task(){
        super();
    }

    //Post: getter needed for persistence.
    @Override
    public String getType(){
        return type;
    }

    //Post: Returns expirationDateTime.
    public LocalDateTime getExpirationDateTime() {
        return this.expirationDateTime;
    }
}
