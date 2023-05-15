package org.calendar.alarms;

import java.time.LocalDateTime;

public class EmailAlarmDecorator extends AlarmDecorator {
    public EmailAlarmDecorator(int id, LocalDateTime ringDateTime, Alarm alarm){
        super(id, ringDateTime, alarm);
    }

    //Post: Makes the "alarm" attribute ring and also sends email to the user.
    @Override
    protected void ring(){
        if(alarm != null) alarm.ring();
        //Send mail algorithm...
    }
}
