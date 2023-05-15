package org.calendar.alarms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailAlarmTest{
    @Mock
    private Alarm emailAlarm;

    //Tests update method when time.now and ringTime don't match.
    @Test
    public void testUpdate() {
        var notRingTime = LocalDateTime.of(1000,1,31,12,0,0);

        doNothing().when(emailAlarm).ring();
        emailAlarm.update(notRingTime);
        verify(emailAlarm, times(0)).ring();
    }

    //Tests ring method.
    @Test
    public void testRing() {
        doNothing().when(emailAlarm).ring();
        emailAlarm.ring();
        verify(emailAlarm, times(1)).ring();
    }
}