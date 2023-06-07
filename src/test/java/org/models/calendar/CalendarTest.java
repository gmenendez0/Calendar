package org.models.calendar;

import org.models.calendar.alarms.Alarm;
import org.models.calendar.alarms.NotificationAlarm;
import org.models.calendar.event.WholeDayEvent;
import org.models.calendar.task.ExpirationTimeTask;
import org.models.calendar.task.WholeDayTask;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CalendarTest {
    private Calendar calendar;
    private WholeDayTask wholeDayTask;

    @Before
    public void initialize(){
        calendar = new Calendar();
        wholeDayTask = new WholeDayTask("title", "description", LocalDate.of(2020, 1, 1));
    }

    //WholeDayTask insertion and get test
    @Test
    public void addGetWholeDayTask() {
        calendar.addAppointment(wholeDayTask);
        var storedWholeDayAppointment = calendar.getAppointment(0);

        assertEquals(wholeDayTask, storedWholeDayAppointment);
    }

    //PeriodTimeTask insertion and get test
    @Test
    public void addGetPeriodTimeTask() {
        var periodTimeTask = new ExpirationTimeTask("title", "description", LocalDateTime.of(2020,1,1,12,0,0));

        calendar.addAppointment(periodTimeTask);
        var storedPeriodAppointment = calendar.getAppointment(0);

        assertEquals(periodTimeTask, storedPeriodAppointment);
    }

    //WholeDayEvent insertion and get test
    @Test
    public void addGetWholeDayEvent() {
        var wholeDayEvent = new WholeDayEvent("title", "description", LocalDate.of(2020, 1, 1));

        calendar.addAppointment(wholeDayEvent);
        var storedWholeDayEvent = calendar.getAppointment(0);

        assertEquals(wholeDayEvent, storedWholeDayEvent);
    }

    //Tests the correct behaviour of the destroyAppointment method.
    @Test
    public void destroyAppointment() {
        calendar.addAppointment(wholeDayTask);
        calendar.destroyAppointment(0);
        var storedAppointment = calendar.getAppointment(0);

        assertTrue(storedAppointment.getDestroyed());
    }

    //Tests the correct behaviour of the edit method.
    @Test
    public void editAppointment(){
        var editedTask = new WholeDayTask("Edited Title.", "description", LocalDate.of(2020, 1, 1));

        calendar.addAppointment(wholeDayTask);
        calendar.editAppointment(0, editedTask);
        var storedAppointment = calendar.getAppointment(0);

        assertEquals(editedTask.getTitle(), storedAppointment.getTitle());
    }

    //Tests the correct behaviour of the addAlarmToAppointment method.
    @Test
    public void addAlarmToAppointment(){
        calendar.addAppointment(wholeDayTask);
        Alarm alarmDeNotif = new NotificationAlarm(1, LocalDateTime.of(2020,1,1,12,0,0));

        boolean expectedSuccess = calendar.addAlarmToAppointment(0, alarmDeNotif);
        boolean expectedFailure = calendar.addAlarmToAppointment(1, alarmDeNotif);

        assertTrue(expectedSuccess);
        assertFalse(expectedFailure);
    }

    //Tests the correct behaviour of the getAppointmentsBetween method.
    @Test
    public void getAppointmentsBetween(){
        var wholeDayEvent = new WholeDayEvent("title", "desc", LocalDate.of(2020, 1, 3));
        var firstDateTime = LocalDateTime.of(2020, 1, 1, 0,0,0);
        var secondDateTime = LocalDateTime.of(2020, 1, 2, 0,0,0);

        calendar.addAppointment(wholeDayTask);
        calendar.addAppointment(wholeDayEvent);

        var selectedAppointments = calendar.getAppointmentsBetween(firstDateTime,  secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }
}