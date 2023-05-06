package org.calendar.alarms;

import junit.framework.TestCase;

import java.time.LocalDateTime;

public class EmailAlarmTest extends TestCase {

    //Tests update method when time.now and ringTime don't match.
    public void testUpdate() {
        Alarm emailAlarm = new EmailAlarm(1, LocalDateTime.of(2020,1,1,12,0,0));
        var now = LocalDateTime.of(2020,1,31,12,0,0);
        boolean result = emailAlarm.update(now);

        assertFalse(result);
    }

    //Tests ring method.
    public void testRing() {
        Alarm emailAlarm = new EmailAlarm(1, LocalDateTime.of(2020,1,1,12,0,0));
        boolean result = emailAlarm.ring();

        assertTrue(result);
    }
}