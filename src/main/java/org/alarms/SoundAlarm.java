package org.alarms;

import org.alarms.Alarm;

import java.time.LocalDateTime;

public class SoundAlarm extends Alarm {

//    private ALGUNALIBRERIA sound;

    //Constructor
    public SoundAlarm(int id, int targetId, LocalDateTime ringLocalDate){
        super(id, targetId, ringLocalDate);
    }

    //Sounds the alarm
    public void dreamAlarm(){

    }
}