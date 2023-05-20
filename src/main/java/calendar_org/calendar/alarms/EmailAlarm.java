package calendar_org.calendar.alarms;

import java.time.LocalDateTime;

public class EmailAlarm extends Alarm {

    private String subtype = "EmailAlarm";

    public EmailAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    public EmailAlarm(){}

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //Post: Rings and sends an email to the user.
    @Override
    protected void ring() {
        //Send mail algorithm...
    }
}
