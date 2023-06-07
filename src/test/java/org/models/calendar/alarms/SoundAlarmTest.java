package org.models.calendar.alarms;


import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class SoundAlarmTest {
    private Alarm spySoundAlarm;

    @Before
    public void initialize(){
        var trueSoundAlarm = new SoundAlarm(1, LocalDateTime.of(2000,1,31,12,0,0));
        spySoundAlarm = spy(trueSoundAlarm);
    }

    //Tests ring method.
    @Test
    public void ring() {
        doNothing().when(spySoundAlarm).ring();

        spySoundAlarm.ring();
        verify(spySoundAlarm, times(1)).ring();
    }
}