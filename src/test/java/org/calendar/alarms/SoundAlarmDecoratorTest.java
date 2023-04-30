package org.calendar.alarms;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class SoundAlarmDecoratorTest {
    //Tests ring method.
    @Test
    public void testRing() {
        //Tests ring when decorator inner alarm == null
        Alarm soundAlarmDecorator = new SoundAlarmDecorator(1, LocalDateTime.of(2020,1,1,12,0,0), null);

        boolean result = soundAlarmDecorator.ring();

        assertTrue(result);

        //Tests ring when innerAlarm != null
        Alarm notifAlarm = new NotificationAlarm(1, LocalDateTime.of(2020,1,1,12,0,0));
        Alarm soundAlarmDecorator2 = new SoundAlarmDecorator(1, LocalDateTime.of(2020,1,1,12,0,0), notifAlarm);

        boolean result2 = soundAlarmDecorator2.ring();

        assertTrue(result2);
    }
}