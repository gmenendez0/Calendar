package org.calendar;

import org.calendar.event.WholeDayEvent;
import org.calendar.task.WholeDayTask;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        var calendar = new Calendar();
        var wholeDayEvent = new WholeDayEvent("hola", "hfds", LocalDate.now());
        var wholeDayTask = new WholeDayTask("ho.a", "dfghd", LocalDate.now());

        calendar.addAppointment(wholeDayEvent);
        calendar.addAppointment(wholeDayTask);
        calendar.getAppointmentsBetween(LocalDateTime.of(2000, 1,1,1,1,0), LocalDateTime.now());
    }
}