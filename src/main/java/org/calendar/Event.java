package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event extends Appointment{

    private boolean isRepeated;
    private Duration duration;
    private Frecuency frecuency;

    public Event(int id, String title, String description,
                 LocalDateTime startEvent, LocalDateTime endingEvent){
        super(id, title, description);
        this.isRepeated = false;
        this.duration = new Duration(startEvent, endingEvent);
    }

    public Event(int id, String title, String description,
                 LocalDate eventDate){
        super(id, title, description);
        this.isRepeated = false;
        this.duration = new Duration(eventDate);
    }

    public boolean eventIsRepeated(){
        return this.isRepeated;
    }

    public void noRepeat(){
        this.isRepeated = false;
        this.frecuency = null;
    }

    public void repeatEvent(char frecuencyType){
        this.isRepeated = true;
        this.frecuency = new Frecuency(frecuencyType);
    }

    public void repeatEvent(char frecuencyType, int interval){
        this.isRepeated = true;
        this.frecuency = new Frecuency(frecuencyType, interval);
    }

    public void repeatEvent(char frecuencyType, ArrayList<DayOfWeek> weekDays){
        this.isRepeated = true;
        this.frecuency = new Frecuency(frecuencyType, weekDays);
    }

    public void changeEventReplays(char frecuencyType){
        this.frecuency.changeFrecuency(frecuencyType);
    }

    public void changeEventReplays(char frecuencyType, int interval){
        this.frecuency.changeFrecuency(frecuencyType, interval);
    }

    public void changeEventReplays(char frecuencyType, ArrayList<DayOfWeek> weekDays){
        this.frecuency.changeFrecuency(frecuencyType, weekDays);
    }

}
