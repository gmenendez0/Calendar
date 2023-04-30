package org.calendar.alarms;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class NotificationAlarmDecoratorTest {
    //Tests ring method.
    @Test
    public void testRing() {
        //Tests ring when decorator inner alarm == null
        Alarm notificationAlarmDecorator = new NotificationAlarmDecorator(1, LocalDateTime.of(2020,1,1,12,0,0), null);

        boolean result = notificationAlarmDecorator.ring();

        assertTrue(result);

        //Tests ring when innerAlarm != null
        Alarm soundAlarm = new SoundAlarm(1, LocalDateTime.of(2020,1,1,12,0,0));
        Alarm notificationAlarmDecorator2 = new NotificationAlarmDecorator(1, LocalDateTime.of(2020,1,1,12,0,0), soundAlarm);

        boolean result2 = notificationAlarmDecorator2.ring();

        assertTrue(result2);
    }
}