package org.calendar.task;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class ExpirationTimeTaskTest {
    final int ONE_DAY = 1;

    private LocalDateTime dateTime;
    private ExpirationTimeTask task;

    @Before
    public void inicialice(){
        dateTime = LocalDateTime.of(2020,1,1,12,0,0);
        task = new ExpirationTimeTask("title", "description", dateTime);
    }

    //Post: Tests that setExpirationDateTime sets the value correctly.
    @Test
    public void setExpirationDateTime() {
        LocalDateTime yesterday = dateTime.minusDays(ONE_DAY);

        task.setExpirationDateTime(yesterday);
        var time = task.getExpirationDateTime();

        assertEquals(yesterday, time);
    }

    //Post: Tests that getExpirationDateTime returns the correct value.
    @Test
    public void getExpirationDateTime() {
        var expirationDateTime = task.getExpirationDateTime();

        assertEquals(dateTime, expirationDateTime);
    }
}