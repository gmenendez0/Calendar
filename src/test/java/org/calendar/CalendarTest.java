package org.calendar;

import org.calendar.alarms.Alarm;
import org.calendar.alarms.NotificationAlarm;
import org.calendar.event.WholeDayEvent;
import org.calendar.task.ExpirationTimeTask;
import org.calendar.task.WholeDayTask;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CalendarTest {

    //Tests add and get methods with most Appointments subclases.
    @Test
    public void addGetAppointment() {
        var calendar = new Calendar();

        //WholeDayTask insertion and get test
        var wholeDayTask = new WholeDayTask("title", "description", LocalDate.now());

        calendar.addAppointment(wholeDayTask);
        var storedWholeDayAppointment = calendar.getAppointment(0);

        assertEquals(wholeDayTask, storedWholeDayAppointment);

        //PeriodTimeTask insertion and get test
        var periodTimeTask = new ExpirationTimeTask("title", "description", LocalDateTime.now().plusDays(1));

        calendar.addAppointment(periodTimeTask);
        var storedPeriodAppointment = calendar.getAppointment(1);

        assertEquals(periodTimeTask, storedPeriodAppointment);

        //WholeDayEvent insertion and get test
        var wholeDayEvent = new WholeDayEvent("title", "description", LocalDate.now());

        calendar.addAppointment(wholeDayEvent);
        var storedWholeDayEvent = calendar.getAppointment(2);

        assertEquals(wholeDayEvent, storedWholeDayEvent);
    }

    //Tests the correct behaviour of the destroyAppointment method.
    @Test
    public void destroyAppointment() {
        var calendar = new Calendar();
        var wholeDayTask = new WholeDayTask("title", "description", LocalDate.now());

        calendar.addAppointment(wholeDayTask);
        calendar.destroyAppointment(0);
        var storedWholeDayAppointment = calendar.getAppointment(0);

        assertTrue(storedWholeDayAppointment.isDestroyed());
    }

    //Tests the correct behaviour of the edit method.
    @Test
    public void editAppointment(){
        var calendar = new Calendar();
        var wholeDayTask = new WholeDayTask("title", "description", LocalDate.now());
        var editedTask = new WholeDayTask("Edited Title.", "description", LocalDate.now());

        calendar.addAppointment(wholeDayTask);
        calendar.editAppointment(0, editedTask);
        var storedWholeDayAppointment = calendar.getAppointment(0);

        assertEquals(editedTask.getTitle(), storedWholeDayAppointment.getTitle());
    }

    //Tests the correct behaviour of the addAlarmToAppointment method.
    @Test
    public void addAlarmToAppointment(){
        Calendar calendario = new Calendar();
        calendario.addAppointment(new WholeDayTask("titulo", "description", LocalDate.now()));
        Alarm alarmDeNotif = new NotificationAlarm(1, LocalDateTime.now());

        boolean expectedSuccess = calendario.addAlarmToAppointment(0, alarmDeNotif);
        boolean expectedFailure = calendario.addAlarmToAppointment(1, alarmDeNotif);

        assertTrue(expectedSuccess);
        assertFalse(expectedFailure);
    }
}