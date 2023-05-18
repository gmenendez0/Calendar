package org.calendar.alarms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SoundAlarm extends Alarm {
    private String subtype = "SoundAlarm";
    public SoundAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    public SoundAlarm(){}

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //Post: Rings making a sound.
    @Override
    protected void ring() {
        //Make sound algorithm...
    }

}
