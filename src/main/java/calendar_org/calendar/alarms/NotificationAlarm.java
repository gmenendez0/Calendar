package calendar_org.calendar.alarms;

import java.time.LocalDateTime;

public class NotificationAlarm extends Alarm {
    private String subtype = "NotificationAlarm";
    public NotificationAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    public NotificationAlarm(){}

    //Post: getter needed for persistence
    public String getSubtype() {
        return subtype;
    }

    //Post: setter needed for persistence
    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //Post: Rings and sends a notification to the user.
    @Override
    public void ring() {
        //Send notif. algorithm...
    }

}
