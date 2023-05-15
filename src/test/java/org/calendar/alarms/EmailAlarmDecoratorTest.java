package org.calendar.alarms;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class EmailAlarmDecoratorTest {
    //Tests ring method when decorator inner alarm != null
    @Test
    public void testRingInnerAlarmNotNull() {
/*        Alarm soundAlarm = new SoundAlarm(1, LocalDateTime.of(2020,1,1,12,0,0));
        Alarm emailAlarmDecorator = new EmailAlarmDecorator(1, LocalDateTime.of(2020,1,1,12,0,0), soundAlarm);
        boolean result = emailAlarmDecorator.ring();

        assertTrue(result);*/
    }

    //Tests ring method when decorator inner alarm == null
    @Test
    public void testRingInnerAlarmNull() {
/*        Alarm emailAlarmDecorator = new EmailAlarmDecorator(1, LocalDateTime.of(2020,1,1,12,0,0), null);
        boolean result = emailAlarmDecorator.ring();

        assertTrue(result);*/
    }
}