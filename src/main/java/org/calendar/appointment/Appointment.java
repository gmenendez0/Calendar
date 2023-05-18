package org.calendar.appointment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.calendar.alarms.Alarm;
import org.calendar.visitor.Visitor;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Appointment{

    @JsonIgnore
    final int NO_ID = -1;

    private int id;
    private String title;
    private String description;
    private boolean completed;
    private boolean destroyed;
    protected final List<Alarm> alarms = new ArrayList<>();

    public Appointment(String title, String description){
        this.id = NO_ID;
        this.title = title;
        this.description = description;
        completed = false;
        destroyed = false;
    }

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

    //Post: Orders the alarms to check if it is time to ring or not.
    public void checkAlarms(LocalDateTime nowTime){
        for (var alarm : alarms) {
            alarm.update(nowTime);
        }
    }

    private Map<String, Object> reportGeneral(){
        Map<String, Object> report = new HashMap<>();
        report.put("id", this.getId());
        report.put("title", this.getTitle());
        report.put("description", this.getDescription());
        report.put("completed", this.getCompleted());
        report.put("destroyed", this.getDestroyed());

        if (!alarms.isEmpty()) {
            List<List<Object>> alarmList = new ArrayList<>();
            for (Alarm a : this.alarms) {
                alarmList.add(a.report());
            }
            report.put("alarms", alarmList);
        } else {
            report.put("alarms", "null");
        }
        return report;
    }

    public abstract Map specificReport();

    public Map report(){
        Map<String, Object> report = new HashMap<>();
        report.putAll(this.reportGeneral());
        report.putAll(this.specificReport());
        return report;
    }

    //Post: Accepts a visitor and returns the "visit" return value.
    public abstract List<Appointment> acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime);
}
