package org.calendar.event.frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Frequency {
    final int FIRST_REPETITION = 1;
    private LocalDate deadline;

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
    public void addDeadline(LocalDate date){
        this.deadline = date;
    }

    //Pre: Receives the number of repetitions of the event and a date.
    //Post: Adds the deadline to the event, counting the number of repetitions.
    public void addDeadlineWithRepetitions(int repetitions, LocalDateTime date){
        for(int i = FIRST_REPETITION; i <= repetitions; i++){
            date = this.nextEventDateTime(date);
        }

        this.addDeadline(date.toLocalDate());
    }

    //Post: Returns the date of following repetition event from the received date, or null if there is no next event.
    public abstract LocalDateTime nextEventDateTime(LocalDateTime date);
}
