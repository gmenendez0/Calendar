package org.models.calendar.alarms;

import java.time.LocalDateTime;

public class SoundAlarm extends Alarm {
    private final String subtype = "SoundAlarm";

    public SoundAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    //Empty constructor for persistence (Jackson).
    public SoundAlarm(){}

    //Post: Rings making a sound.
    @Override
    public void ring() {
        //Make sound algorithm...
    }

}
