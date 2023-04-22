package org.calendar;

import org.calendar.task.Task;
import org.junit.Test;
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
}