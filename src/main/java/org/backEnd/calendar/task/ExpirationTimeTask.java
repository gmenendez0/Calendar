package org.backEnd.calendar.task;

import org.backEnd.calendar.visitor.Visitor;
import org.backEnd.calendar.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public class ExpirationTimeTask extends Task {

    private String subtype = "ExpirationTimeTask";

    public ExpirationTimeTask(String title, String description, LocalDateTime expirationDateTime) {
        super(title, description, expirationDateTime);
    }

    //Empty constructor for persistence (Jackson).
    public ExpirationTimeTask(){}

    //Post: getter needed for persistence.
    public String getSubtype(){
        return this.subtype;
    }

    //Post: setter needed for persistence.
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
