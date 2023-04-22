package org.calendar;

import org.calendar.task.WholeDayTask;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class WholeDayTaskTest {
    //Post: Tests the return value of getStartDate.
    @Test
    public void getStartDate() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        var wholeDayTask = new WholeDayTask(1, "title", "description", LocalDate.of(2020, 1, 1), LocalDateTime.of(2020, 1, 1, 0, 0));

        var taskStartDate = wholeDayTask.getStartDateTime().toLocalDate();

        assertEquals(startDate, taskStartDate);
    }

    //Post: Tests the correct behaviour of setStartDate.
    @Test
    public void setStartDate() {
        LocalDate newStartDate = LocalDate.of(2020, 1, 2);
        var wholeDayTask = new WholeDayTask(1, "title", "description", LocalDate.of(2020, 1, 1), LocalDateTime.of(2020, 1, 1, 0, 0));

        wholeDayTask.setStartDate(newStartDate);
        LocalDate startDate = wholeDayTask.getStartDateTime().toLocalDate();

        assertEquals(newStartDate, startDate);
    }
}