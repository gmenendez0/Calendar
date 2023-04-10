package org.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task extends Appointment {
    final int ONE_DAY = 1;

    private LocalDateTime expirationDateTime;
    private boolean wholeDay;

    //! DUDA! ESTO DEBERIA SER ASI? DEBERIA SER EL OBJETO TASK EL QUE DETERMINE DEPENDIENDO DE SI RECIBE UNA LocalDate O UNA LocalDateTime SI WHOLEDAY ES TRUE O FALSE?
    //! O DEBERIA LLEGAR TODO MAS CLARO DESDE ARRIBA, Y EL OBJETO TASK SIMPLEMENTE ORGANIZAR LO QUE RECIBE?

    //Constructs a task with wholeDay = true;
    public Task(int id, String title, String description, LocalDate startDate) {
        super(id, title, description);
        this.wholeDay = true;
        expirationDateTime = startDate.plusDays(ONE_DAY).atStartOfDay();
    }

    //Constructs a task with wholeDay = false;
    public Task(int id, String title, String description, LocalDateTime expirationDateTime) {
        super(id, title, description);
        this.wholeDay = false;
        this.expirationDateTime = expirationDateTime;
    }

    //Post: Updates the expirationDateTime
    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    //Post: Returns expirationDateTime
    public LocalDateTime getExpirationDateTime() {
        return this.expirationDateTime;
    }

    //Pre: Task must not be wholeDay.
    //Post: Sets wholeDay = true and sets the expirationDateTime = expirationDate + 1 day at 00:00:00 hours.
    public void activateWholeDay() {
        if (!isWholeDay()){
            wholeDay = true;
            LocalDate startDate = expirationDateTime.toLocalDate();
            expirationDateTime = startDate.plusDays(ONE_DAY).atStartOfDay();
        }
    }

    //Pre: Task must be wholeDay. Method must receive a new expirationDateTime.
    //Post: Sets wholeDay = false and updates the expirationDateTime.
    public void deactivateWholeDay(LocalDateTime expirationDateTime) {
        if (isWholeDay()){
            wholeDay = false;
            this.expirationDateTime = expirationDateTime;
        }
    }

    //Post: Returns wholeDay.
    public boolean isWholeDay() {
        return wholeDay;
    }
}
