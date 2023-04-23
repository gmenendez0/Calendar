package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Frequency {
    final int FIRST_REPETITION = 1;
    private LocalDate deadline;

    //Returns false if there is a next event, true otherwise
    protected boolean noNextEvent(LocalDate day){
        return (hasADeadline() && hasExceededDeadline(day));
    }

    //Post: Returns true if the date received is after the deadLine, false otherwise.
    public boolean hasExceededDeadline(LocalDate date){
        return date.isAfter(this.deadline);
    }

    //Post: returns true if there is a deadline. If not, return false.
    public boolean hasADeadline(){
        return this.deadline != null;
    }

    //Pre: receive the date that will be the deadline.
    //Post: adds the deadline to the event.
    public void addDeadline(LocalDate date){
        this.deadline = date;
    }

    //Pre: receives the number of repetitions of the event and a date.
    //Post: adds the deadline to the event, counting the number of repetitions.
    public void addDeadlineWithRepetitions(int repetitions, LocalDateTime date){
        for(int i = FIRST_REPETITION; i <= repetitions; i++){
            date = this.nextEventDateTime(date);
        }
        this.addDeadline(date.toLocalDate());
    }

    //Pre: receive a date and time what will be the deadline.
    //Post: returns the next event date, null if there is no next event.
    public abstract LocalDateTime nextEventDateTime(LocalDateTime date);
}
