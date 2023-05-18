package org.calendar.task;

import org.calendar.appointment.Appointment;
import org.calendar.visitor.Visitor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ExpirationTimeTask extends Task {

    private String subtype = "ExpirationTimeTask";

    public ExpirationTimeTask(String title, String description, LocalDateTime expirationDateTime) {
        super(title, description, expirationDateTime);
    }

    public ExpirationTimeTask(){}

    public String getSubtype(){
        return this.subtype;
    }

    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //Post: Sets the expirationDateTime.
    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    //@inheritDoc
    @Override
    public List<Appointment> acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        return visitor.visitExpirationTimeTask(this, firstDayTime, secondDayTime);
    }

}
