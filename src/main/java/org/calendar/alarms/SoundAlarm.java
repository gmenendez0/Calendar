package org.calendar.alarms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SoundAlarm extends Alarm {
    public SoundAlarm(int id, LocalDateTime ringDateTime){
        super(id, ringDateTime);
    }

    //Post: Rings making a sound.
    @Override
    protected void ring() {
        //Make sound algorithm...
    }

    @Override
    public List report(){
        List<Object> report = new ArrayList<>();
        report.add("SoundAlarm");
        report.add(this.ringDateTime);
        return report;
    }
}
