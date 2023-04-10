package org.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Expiration {
    final int ONE_DAY = 1;

    private boolean wholeDay;
    private LocalDateTime expirationDateTime;

    //Constructs an Expiration object with wholeDay = false.
    public Expiration(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
        wholeDay = false;
    }

    //Constructs an Expiration object with wholeDay = true and expirationDateTime = the next day to the start date at 00:00:00.
    public Expiration(LocalDate startDate) {
        this.wholeDay = true;
        this.expirationDateTime = startDate.plusDays(ONE_DAY).atStartOfDay();
    }

    //Post: Sets wholeDay.
    public void setWholeDay(boolean wholeDay) {
        this.wholeDay = wholeDay;
    }

    //Post: Returns wholeDay.
    public boolean isWholeDay() {
        return wholeDay;
    }

    //Post: Sets the  expirationDateTime
    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    //Post: Returns the expirationDateTime
    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }
}
