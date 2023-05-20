package org.calendar.alarms;

import calendar_org.calendar.alarms.EmailAlarm;
import calendar_org.calendar.alarms.SoundAlarmDecorator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDateTime;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SoundAlarmDecoratorTest {
    //Tests ring method when decorator inner alarm != null
    @Test
    public void testRingInnerAlarmNotNull() {
        EmailAlarm alarmMock = mock(EmailAlarm.class);

        var soundAlarmDecorator = new SoundAlarmDecorator(1, LocalDateTime.of(2000,1,31,12,0,0), alarmMock);
        soundAlarmDecorator.ring();

        verify(alarmMock, times(1)).ring();
    }

}