package org.calendar;

import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class TaskTest {
    final int ONE_DAY = 1;

    //Post: Tests that setExpirationDateTime sets the value correctly.
    @Test
    public void setExpirationDateTime() {
        var task = new Task(1, "title", "description", LocalDateTime.now());
        LocalDateTime yesterday = LocalDateTime.now().minusDays(ONE_DAY);

        task.setExpirationDateTime(yesterday);
        var time = task.getExpirationDateTime();

        assertEquals(yesterday, time);
    }

    //Post: Tests that getExpirationDateTime returns the correct value.
    @Test
    public void getExpirationDateTime() {
        LocalDateTime now = LocalDateTime.now();
        var task = new Task(1, "title", "description", now);

        var time = task.getExpirationDateTime();

        assertEquals(now, time);
    }

    //Post: Tests that activateWholeDay sets the correct expirationDateTime.
    @Test
    public void activateWholeDayExpirationDateTime() {
        LocalDateTime now = LocalDateTime.now();
        var task = new Task(1, "title", "description", LocalDateTime.now());
        LocalDateTime expectedDateTime = now.toLocalDate().plusDays(ONE_DAY).atStartOfDay();

        task.activateWholeDay();
        LocalDateTime expirationDateTime = task.getExpirationDateTime();

        assertEquals(expectedDateTime, expirationDateTime);
    }

    //Post: Tests that activateWholeDay sets the correct value at isWholeDay (true).
    @Test
    public void activateWholeDay() {
        var task = new Task(1, "title", "description", LocalDateTime.now());

        task.activateWholeDay();
        boolean isWholeDay = task.isWholeDay();

        assertTrue(isWholeDay);
    }

    //Post: Tests that deactivateWholeDay sets the correct value at isWholeDay (false).
    @Test
    public void deactivateWholeDay() {
        LocalDate today = LocalDate.now();
        var task = new Task(1, "title", "description", today);

        task.deactivateWholeDay(LocalDateTime.now());
        boolean isWholeDay = task.isWholeDay();

        assertFalse(isWholeDay);
    }

    //Post: Tests that deactivateWholeDay sets the correct expirationDateTime.
    @Test
    public void deactivateWholeDayExpirationDateTime() {
        LocalDate today = LocalDate.now();
        var task = new Task(1, "title", "description", today);
        LocalDateTime now = LocalDateTime.now();

        task.deactivateWholeDay(now);
        LocalDateTime expirationDateTime = task.getExpirationDateTime();

        assertEquals(now, expirationDateTime);
    }

    //Post: Tests isWholeDay return value.
    @Test
    public void isWholeDay() {
        var task = new Task(1, "title", "description", LocalDate.now());

        boolean isWholeDay = task.isWholeDay();

        assertTrue(isWholeDay);
    }
}