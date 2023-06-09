package org.models.calendar.task;

import org.models.calendar.appointment.Appointment;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.models.calendar.visitor.appointment_details.AppointmentDetailsVisitor;

import java.time.LocalDateTime;

//tags for json deserialization
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "subtype")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ExpirationTimeTask.class, name = "ExpirationTimeTask"),
        @JsonSubTypes.Type(value = WholeDayTask.class, name = "WholeDayTask")
})

public abstract class Task extends Appointment {
    protected LocalDateTime expirationDateTime;

    private final String type = "Task";

    public Task(String title, String description, LocalDateTime expirationDateTime) {
        super(title, description);
        this.expirationDateTime = expirationDateTime;
    }

    //Empty constructor for persistence (Jackson).
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

    //@inheritDoc
    @Override
    public String acceptVisitorDetailsDates(AppointmentDetailsVisitor visitor) {
        return visitor.detailsOfDatesTask(this.getExpirationDateTime());
    }

    //@inheritDoc
    @Override
    public String acceptVisitorDetailsFrequency(AppointmentDetailsVisitor visitor){
        return "";
    }
}
