package org.models.calendar.event;

import org.models.calendar.alarms.Alarm;
import org.models.calendar.event.frequency.Frequency;
import org.models.calendar.visitor.Visitor;
import org.models.calendar.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PeriodTimeEvent extends Event {
    private String subtype = "PeriodTimeEvent";

    public PeriodTimeEvent(String title, String description, LocalDateTime startDateTime, LocalDateTime endingDateTime) {
        super(title, description, startDateTime, endingDateTime);
    }

    //Empty constructor for persistence (Jackson).
    public PeriodTimeEvent(){}

    //Post: getter needed for persistence.
    public String getSubtype(){
        return this.subtype;
    }

    //Post: setter needed for persistence.
    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //Post: Sets the startDateTime of the event.
    public void setEventStartDateTime(LocalDateTime EventStartDateTime){
        this.startDateTime = EventStartDateTime;
    }

    //Post: Sets the endingDateTime of the event.
    public void setEventEndingDateTime(LocalDateTime EventEndingDateTime){
        this.endingDateTime = EventEndingDateTime;
    }

    //Post: Sets the event's frequency.
    public void setFrequency(Frequency frequency){
        this.frequency = frequency;
    }

    //Post: Returns event's frequency.
    public Frequency getFrequency(){
        return this.frequency;
    }

    //@inheritDoc
    @Override
    public List<Appointment> acceptVisitor(Visitor visitor, LocalDateTime firstDayTime, LocalDateTime secondDayTime) {
        return visitor.visitPeriodTimeEvent(this, firstDayTime, secondDayTime);
    }

    //Post: returns from the next event of the current event.
    @Override
    public Event getNextRepetition() {
        LocalDateTime dateTimeStartEvent = getStartDateTime();
        if (!thereIsNextRepetition(dateTimeStartEvent)) return null;

        String title = getTitle();
        String description = getDescription();
        LocalDateTime dateTimeStartRepeat = getNextRepetitionStartDateTime(dateTimeStartEvent);
        LocalDateTime dateTimeEndRepeat = getNextRepetitionEndingDateTime(getEndingDateTime());

        var repeatedEvent = new PeriodTimeEvent(title, description, dateTimeStartRepeat, dateTimeEndRepeat);
        repeatedEvent.setFrequency(this.getFrequency());

        return repeatedEvent;
    }

    //@inheritDoc
    //Format example: "Title   Event start date-time: 1/1/23 12:12:23   Event ending date-time: 1/1/23 12:20:00   Completed: Yes"
    @Override
    public String formatToString(){
        String isCompleted = "No";
        if(isCompleted()) isCompleted = "Yes";

        return this.getTitle() + "   " + "\n Event start date-time: " + this.getStartDateTime().toString() + "\n Event ending date-time: " + getEndingDateTime().toString() + "\n Completed: " + isCompleted;
    }

    @Override
    public Map<String, String> dataToMapOfString(){

        Map<String, String> hashData = new HashMap<>();

        hashData.put("Title", this.getTitle());
        hashData.put("Description", this.getDescription());
        hashData.put("Type", this.type);
        hashData.put("DateImportant", "Initial Date: " + this.getStartDateTime().toString()
                                     + "\nFinal Date: " + this.getEndingDateTime().toString());
        if (this.withAlarms()){
            String listAlarms = "";
            for (Alarm alarm : this.getAlarms()) {
                listAlarms += alarm.toString() + "\n";
            }
            hashData.put("Alarms", listAlarms);
        } else {
            hashData.put("Alarms", "This event has no alarms.");
        }

        if (this.isRepeated()){
            hashData.put("Frequency", this.getFrequency().toString());
        } else {
            hashData.put("Frequency", "This event has no frequency.");
        }

        return hashData;
    }
}
