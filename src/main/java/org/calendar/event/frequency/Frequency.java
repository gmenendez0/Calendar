package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Frequency {
    final int FIRST_REPETITION = 1;
    protected LocalDate deadline;

    //Post: Returns false if there is a next event, true otherwise
    protected boolean noNextEvent(LocalDate day){
        return (hasADeadline() && hasExceededDeadline(day));
    }

    //Post: Returns true if the date received is after the deadLine, false otherwise.
    public boolean hasExceededDeadline(LocalDate date){
        return date.isAfter(this.deadline);
    }

    //Post: Returns true if there is a deadline. If not, returns false.
    public boolean hasADeadline(){
        return this.deadline != null;
    }

    //Post: Adds the deadline to the event.
    public void setDeadline(LocalDate date){
        this.deadline = date;
    }

    //Pre: Receives the number of repetitions of the event and a date.
    //Post: Adds the deadline to the event, counting the number of repetitions.
    public void addDeadlineWithRepetitions(int repetitions, LocalDateTime date){
        for(int i = FIRST_REPETITION; i <= repetitions; i++){
            date = this.nextRepetitionDateTime(date);
        }

        this.setDeadline(date.toLocalDate());
    }

    //Pre: receives the LocalTime that represents the time at which an event date ends.
    //Post: returns the deadline LocalDate converted to a LocalDateTime
    public LocalDateTime getDeadlineDateTime(LocalTime finalTime){
        if (this.hasADeadline()) {
            return this.deadline.atTime(finalTime);
        }
        return null;
    }

    //Post: returns true if there is a next date for the received date, false otherwise.
    public boolean hasNextRepetition(LocalDateTime date) {
        return this.nextRepetitionDateTime(date) != null;
    }

    //Post: returns the next repetition date with respect to the received date, or null if there is no next repetition.
    public abstract LocalDateTime nextRepetitionDateTime(LocalDateTime date);

    public List<Object> report(){
        List<Object> report = new ArrayList<>();
        this.subTypeFrequency(report);
        report.add(this.deadline);
        return report;
    }

    public abstract void subTypeFrequency(List<Object> report);
}
