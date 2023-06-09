package org.models.calendar.alarms;

import java.time.LocalDateTime;

public class NotificationAlarm extends Alarm {
    private final String subtype = "NotificationAlarm";

    public NotificationAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    //Empty constructor for persistence (Jackson).
    public NotificationAlarm(){}

    //Post: Rings and sends a notification to the user.
    @Override
    public void ring() {
        //Send notif. algorithm...
    }

    //Post: Returns a string representation of the alarm.
    public String toString(){
        return "Alarm Type: " + subtype + " | Ring Time: " + this.ringDateTime.toString();
    }
}
