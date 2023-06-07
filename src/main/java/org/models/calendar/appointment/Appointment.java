package org.models.calendar.appointment;

import org.models.calendar.event.Event;
import org.models.calendar.task.Task;
import org.models.calendar.visitor.Visitor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.models.calendar.alarms.Alarm;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

//tags for json deserialization
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "subtype")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Event.class, name = "PeriodTimeEvent"),
        @JsonSubTypes.Type(value = Event.class, name = "WholeDayEvent"),
        @JsonSubTypes.Type(value = Task.class, name = "ExpirationTimeTask"),
        @JsonSubTypes.Type(value = Task.class, name = "WholeDayTask"),
})

public abstract class Appointment implements Serializable {
    private int id;
    private String title;
    private String description;
    private boolean completed;
    private boolean destroyed;
    protected final List<Alarm> alarms = new ArrayList<>();
    @JsonIgnore
    final int NO_ID = -1;

    public Appointment(String title, String description){
        this.id = NO_ID;
        this.title = title;
        this.description = description;
        completed = false;
        destroyed = false;
    }

    //Empty constructor for persistence (Jackson).
    public Appointment(){}

    //Post: Sets the id.
    public void setId(int id){
        this.id = id;
    }

    //Post: Returns the id.
    public int getId(){
        return id;
    }

    //Post: Sets the title.
    public void setTitle(String title) {
        this.title = title;
    }

    //Post: Sets the description.
    public void setDescription(String description){
        this.description = description;
    }

    //Post: Sets the "completed" attribute.
    public void setCompleted(boolean completed){
        this.completed = completed;
    }

    //Post: Returns completed attribute.
    public boolean isCompleted(){
        return completed;
    }

    //Post: Sets the destroyed attribute as true.
    public void setDestroyed(){
        destroyed = true;
    }

    //Post: Returns the title.
    public String getTitle(){
        return title;
    }

    //Post: Returns the description.
    public String getDescription(){
        return description;
    }

    //Post: Returns the state of the appointment. True if completed, false otherwise
    public boolean getCompleted(){
        return completed;
    }

    //Post: Returns the state of the appointment. True if destroyed, false otherwise
    public boolean getDestroyed(){
        return destroyed;
    }

    //Post: Adds an alarm to the alarm array.
    public void addAlarm(Alarm alarm){
        alarms.add(alarm);
    }

    //post: Removes the alarm with id = alarmId. Returns true in case of success, false otherwise.
    public boolean removeAlarm(int alarmId){
        boolean isRemoved = false;
        int i = 0;

        while(!isRemoved && i < alarms.size()){
            if (alarms.get(i).getId() == alarmId){
                isRemoved = true;
                alarms.remove(i);
            }
            i++;
        }

        return isRemoved;
    }

    //Post: getter needed for persistence
    public List<Alarm> getAlarms(){
        return alarms;
    }

    //Post: Orders the alarms to check if it is time to ring or not.
    public void checkAlarms(LocalDateTime nowTime){
        for (var alarm : alarms) {
            alarm.update(nowTime);
        }
    }

    //Post: getter needed for persistence.
    public abstract String getType();
    //Post: Accepts a visitor and returns the "visit" return value.
    public abstract List<Appointment> acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime);

    //Post: Returns a string with all the crucial appointment's info.
    public abstract String formatToString();
}
