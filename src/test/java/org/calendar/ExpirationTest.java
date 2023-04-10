package org.calendar;

import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class ExpirationTest {
    final int ONE_DAY = 1;

    //Post: Tests getExpirationDateTime method.
    @Test
    public void getExpirationDateTime() {
        LocalDateTime initialTime = LocalDateTime.now();
        var expiration = new Expiration(initialTime);

        LocalDateTime time = expiration.getExpirationDateTime();

        assertEquals(initialTime, time);
    }

    //Post: Tests setExpirationDateTime method using not wholeDay constructor.
    @Test
    public void setExpirationDateTime() {
        LocalDateTime initialTime = LocalDateTime.now();
        var expiration = new Expiration(initialTime);
        LocalDateTime finalTime = LocalDateTime.now();

        expiration.setExpirationDateTime(finalTime);
        LocalDateTime time = expiration.getExpirationDateTime();

        assertEquals(finalTime, time);
    }

    //Post: Tests wholeDayConstructor.
    @Test
    public void wholeDayConstructor(){
        LocalDate taskDay = LocalDate.now();
        var expiration = new Expiration(taskDay);

        LocalDateTime expirationDateTime = expiration.getExpirationDateTime();
        LocalDateTime expectedDateTime = taskDay.plusDays(ONE_DAY).atStartOfDay();

        assertEquals(expectedDateTime, expirationDateTime);
    }

    //Post: Tests setWholeDay method using wholeDay constructor.
    @Test
    public void setWholeDay(){
        LocalDate taskDay = LocalDate.now();
        var expiration = new Expiration(taskDay);

        expiration.setWholeDay(false);
        boolean isWholeDay = expiration.isWholeDay();

        assertFalse(isWholeDay);
    }

    //Post: Tests isWholeDay method using wholeDay constructor.
    @Test
    public void isWholeDay(){
        LocalDate taskDay = LocalDate.now();
        var expiration = new Expiration(taskDay);

        boolean isWholeDay = expiration.isWholeDay();

        assertTrue(isWholeDay);
    }
}