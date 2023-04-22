package org.calendar.task;

import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class ExpirationTimeTaskTest {

    final int ONE_DAY = 1;

    //Post: Tests that setExpirationDateTime sets the value correctly.
    @Test
    public void setExpirationDateTime() {
        var task = new ExpirationTimeTask(1, "title", "description", LocalDateTime.now());
        LocalDateTime yesterday = LocalDateTime.now().minusDays(ONE_DAY);

        task.setExpirationDateTime(yesterday);
        var time = task.getExpirationDateTime();

        assertEquals(yesterday, time);
    }

    //Post: Tests that getExpirationDateTime returns the correct value.
    @Test
    public void getExpirationDateTime() {
        LocalDateTime now = LocalDateTime.now();
        var task = new ExpirationTimeTask(1, "title", "description", now);

        var time = task.getExpirationDateTime();

        assertEquals(now, time);
    }
}