package org.models.calendar.alarms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDateTime;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailAlarmDecoratorTest {
    //Tests ring method when decorator inner alarm != null
    @Test
    public void testRingInnerAlarmNotNull() {
        NotificationAlarm alarmMock = mock(NotificationAlarm.class);

        var emailAlarmDecorator = new EmailAlarmDecorator(1, LocalDateTime.of(2000,1,31,12,0,0), alarmMock);
        emailAlarmDecorator.ring();

        verify(alarmMock, times(1)).ring();
    }
}