package org.calendar.event;

import org.calendar.appointment.Appointment;
import org.calendar.event.frequency.Frequency;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Event extends Appointment {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endingDateTime;
    private Frequency frequency;

    public Event(String title, String description, LocalDateTime startDateTime, LocalDateTime endingDateTime){
        super(title, description);
        this.startDateTime = startDateTime;
        this.endingDateTime = endingDateTime;
        frequency = null;
    }

    public Event(){}

    //Post: Returns true if the event repeats, false otherwise.
    public boolean isRepeated(){
         return this.frequency != null;
    }

    //Post: Sets frequency to null.
    public void setNoRepeat(){
        this.frequency = null;
    }

    //Post: Sets the event's frequency.
    public void setEventFrequency(Frequency frequency){
        this.frequency = frequency;
    }

    //Post: Returns the next date and time of the event with respect to the date and time received, or null if there is no repetition or no next event.
    private LocalDateTime getNextEventRegardDateTime(LocalDateTime date){
        if(isRepeated()) return frequency.nextRepetitionDateTime(date);
        return null;
    }

    //Post: Returns true if firstDateTime is after or equal to secondDateTime
    private boolean dateTimeIsAfterOrEqual(LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        return firstDateTime.isAfter(secondDateTime) || firstDateTime.isEqual(secondDateTime);
    }

    //Post: Returns the immediate next event s dateTime from the date passed, returns null if it does not repeat.
    private LocalDateTime findNextImmediateDate(LocalDateTime startOrEnding, LocalDateTime date){
        if (!this.isRepeated()) return null;
        
        while(dateTimeIsAfterOrEqual(date, startOrEnding)){
            startOrEnding = this.getNextEventRegardDateTime(startOrEnding);
            if (startOrEnding == null) return null;
        }
        
        return startOrEnding;
    }

    //Post: Returns the immediate next event s startDateTime from the date passed, returns null if it does not repeat.
    public LocalDateTime getNextRepetitionStartDateTime(LocalDateTime date) {
        return findNextImmediateDate(this.startDateTime, date);
    }

    //Post: Returns the immediate next event s endingDateTime from the date passed, returns null if it does not repeat.
    public LocalDateTime getNextRepetitionEndingDateTime(LocalDateTime date){
        return findNextImmediateDate(this.endingDateTime, date);
    }

    //post: Returns the start date time of the event.
    public LocalDateTime getStartDateTime(){
        return this.startDateTime;
    }

    //Post: Returns the ending date time of the event.
    public LocalDateTime getEndingDateTime(){
        return this.endingDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndingDateTime(LocalDateTime endingDateTime) {
        this.endingDateTime = endingDateTime;
    }

    //Post: Returns true if there is an event after the received date, false otherwise.
    public boolean thereIsNextRepetition(LocalDateTime date){
        return this.frequency.hasNextRepetition(date);
    }

    //Post: Returns the last repeated event s endingDateTime. If repetition is infinite, it will return null.
    public LocalDateTime getLastRepetitionEndingDateTime(){
        LocalTime finalTime = this.endingDateTime.toLocalTime();
        return this.frequency.getDeadlineDateTime(finalTime);
    }

    //Post: Returns event s frequency.
    protected Frequency getFrequency(){
        return this.frequency;
    }

    @Override
    public Map specificReport(){
        Map<String, Object> report = new HashMap<>();
        report.put("Type", "Event");
        specificSubType(report);
        List<LocalDateTime> duration = new ArrayList<>();
        duration.add(this.startDateTime);
        duration.add(this.endingDateTime);
        report.put("Duration", duration);
        report.put("Frequency", this.frequency.report());
        return report;
    }

    //Post: returns from the next repetition of the current event.
    public abstract Event getNextRepetition();

    public abstract void specificSubType(Map<String, Object> report);
}