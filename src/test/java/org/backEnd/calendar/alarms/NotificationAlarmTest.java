package org.backEnd.calendar.alarms;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class NotificationAlarmTest {
    private Alarm spyNotificationAlarm;

    @Before
    public void initialize(){
        var trueNotificationAlarm = new NotificationAlarm(1, LocalDateTime.of(2000,1,31,12,0,0));
        spyNotificationAlarm = spy(trueNotificationAlarm);
    }

    //Tests ring method.
    @Test
    public void ring() {
        doNothing().when(spyNotificationAlarm).ring();

        spyNotificationAlarm.ring();
        verify(spyNotificationAlarm, times(1)).ring();
    }
}