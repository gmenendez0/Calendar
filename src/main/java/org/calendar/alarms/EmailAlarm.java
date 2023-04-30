package org.calendar.alarms;

import java.time.LocalDateTime;

public class EmailAlarm extends Alarm {
    public EmailAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    //Post: Rings and sends an email to the user.
    @Override
    protected boolean ring() {
        return true;
    }
}
