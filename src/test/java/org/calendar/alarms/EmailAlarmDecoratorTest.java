package org.calendar.alarms;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class EmailAlarmDecoratorTest {
    //Tests ring method.
    @Test
    public void testRing() {
        //Tests ring when decorator inner alarm == null
        Alarm emailAlarmDecorator = new EmailAlarmDecorator(1, LocalDateTime.of(2020,1,1,12,0,0), null);
        boolean result = emailAlarmDecorator.ring();

        assertTrue(result);

        //Tests ring when innerAlarm != null
        Alarm soundAlarm = new SoundAlarm(1, LocalDateTime.of(2020,1,1,12,0,0));
        Alarm emailAlarmDecorator2 = new EmailAlarmDecorator(1, LocalDateTime.of(2020,1,1,12,0,0), soundAlarm);
        boolean result2 = emailAlarmDecorator2.ring();

        assertTrue(result2);
    }
}