package org.calendar;

import java.time.LocalDateTime;

public class NotificationAlarm extends Alarm{
    private String message;

    //Constructor
    public NotificationAlarm(int id, int targetId, LocalDateTime ringDateTime, String message){
        super(id, targetId, ringDateTime);
        this.message = message;
    }

    //Send the alarm notification
    public void sendNotification(){

    }

    //Change the message.
    public void setMessage(String message){
        this.message = message;
    }
}
