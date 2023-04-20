package org.calendar;

import java.time.LocalDateTime;

public class EmailAlarm extends Alarm{
    private String message;
    private String email;

    //Constructor.
    public EmailAlarm(int id, int targetId, LocalDateTime ringLocalDate, String message, String email){
        super(id, targetId, ringLocalDate);
        this.message = message;
        this.email = email;
    }

    //Change the message.
    public void setMessage(String message){
        this.message = message;
    }

    //Change the email.
    public void setEmail(String email){
        this.email = email;
    }

    //send the alarm by email
    public void sendEmail(){

    }
}
