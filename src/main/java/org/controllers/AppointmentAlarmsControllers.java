package org.controllers;

import javafx.animation.AnimationTimer;
import org.models.calendar.alarms.Alarm;
import org.models.calendar.appointment.Appointment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;

public class AppointmentAlarmsControllers {
    private final Timer timer = new Timer();

    private final MessageControllers messageControllers = new MessageControllers();

    private void showNotificationAlarm(Appointment appointmentActive) {
        messageControllers.success("It's " + appointmentActive.getType() + " time: " + appointmentActive.getTitle());
    }

    public void active(Alarm alarm, Appointment appointment){
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                boolean EQUAL_TIME = alarm.checkTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
                if(EQUAL_TIME) {
                    showNotificationAlarm(appointment);
                    stop();
                }
            }
        };
        animationTimer.start();
    }
}
