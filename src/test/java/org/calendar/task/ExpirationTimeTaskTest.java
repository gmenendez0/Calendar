package org.calendar.task;

import org.junit.Test;

import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class ExpirationTimeTaskTest {
    final int ONE_DAY = 1;

    //Post: Tests that setExpirationDateTime sets the value correctly.
    @Test
    public void setExpirationDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2020,1,1,12,0,0);
        var task = new ExpirationTimeTask("title", "description", dateTime);
        LocalDateTime yesterday = dateTime.minusDays(ONE_DAY);

        task.setExpirationDateTime(yesterday);
        var time = task.getExpirationDateTime();

        assertEquals(yesterday, time);
    }

    //Post: Tests that getExpirationDateTime returns the correct value.
    @Test
    public void getExpirationDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2020,1,1,12,0,0);
        var task = new ExpirationTimeTask("title", "description", dateTime);

        var expirationDateTime = task.getExpirationDateTime();

        assertEquals(dateTime, expirationDateTime);
    }
}