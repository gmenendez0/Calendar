package calendar_org.calendar.alarms;

import java.time.LocalDateTime;

public class EmailAlarm extends Alarm {

    private String subtype = "EmailAlarm";

    public EmailAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    public EmailAlarm(){}

    //Post: getter needed for persistence.
    public String getSubtype() {
        return subtype;
    }

    //Post: getter needed for persistence.
    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //Post: Rings and sends an email to the user.
    @Override
    public void ring() {
        //Send mail algorithm...
    }
}
