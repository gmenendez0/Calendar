package org.calendar.appointment;

import org.calendar.alarms.Alarm;

import java.util.ArrayList;
import java.util.List;

public abstract class Appointment{
    final int NO_ID = -1;

    private int id;
    private String title;
    private String description;
    private boolean completed;
    private boolean destroyed;
    protected final List<Alarm> alarms = new ArrayList<>();

    //Constructor.
    public Appointment(String title, String description){
        this.id = NO_ID;
        this.title = title;
        this.description = description;
        completed = false;
        destroyed = false;
    }

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

    //Post: Sets the destroyed attribute as true.
    public void destroy(){
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
    public boolean isCompleted(){
        return completed;
    }

    //Post: Returns the state of the appointment. True if destroyed, false otherwise
    public boolean isDestroyed(){
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

    //Post: Orders the alarms to check if it is time to ring or not.
    public void checkAlarms(){
        for (var alarm : alarms) {
            alarm.update();
        }
    }
}
