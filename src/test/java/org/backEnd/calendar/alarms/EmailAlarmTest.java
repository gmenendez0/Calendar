package org.backEnd.calendar.alarms;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailAlarmTest{
    private Alarm spyEmailAlarm;

    @Before
    public void initialize(){
        var trueEmailAlarm = new EmailAlarm(1, LocalDateTime.of(2000,1,31,12,0,0));
        spyEmailAlarm = spy(trueEmailAlarm);
    }

    //Tests update method when time.now and ringTime don't match. As "ring" method is not expected to be called, test shall not use "doNothing().when(spyEmailAlarm).ring()".
    @Test
    public void testUpdate() {
        var notRingTime = LocalDateTime.of(1000,1,31,12,0,0);

        spyEmailAlarm.update(notRingTime);

        verify(spyEmailAlarm, times(0)).ring();
    }

    //Tests ring method.
    @Test
    public void testRing() {
        doNothing().when(spyEmailAlarm).ring();

        spyEmailAlarm.ring();

        verify(spyEmailAlarm, times(1)).ring();
    }
}