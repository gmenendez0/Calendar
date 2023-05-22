package org.calendar.alarms;

import java.time.LocalDateTime;

public class SoundAlarm extends Alarm {
    private String subtype = "SoundAlarm";
    public SoundAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    //Empty constructor for persistence (Jackson).
    public SoundAlarm(){}

    //Post: getter needed for persistence
    public String getSubtype() {
        return subtype;
    }

    //Post: setter needed for persistence
    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //Post: Rings making a sound.
    @Override
    public void ring() {
        //Make sound algorithm...
    }

}
