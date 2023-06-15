package org.controllers;

import javafx.animation.AnimationTimer;
import org.models.calendar.alarms.Alarm;
import org.models.calendar.appointment.Appointment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AppointmentAlarmsControllers {
    private final MessageControllers messageControllers = new MessageControllers();

    //Pre: receives the alarm appointment.
    //Post: show alarm notification.
    private void showNotificationAlarm(Appointment appointmentActive) {
        messageControllers.success("It's " + appointmentActive.getType() + " time: " + appointmentActive.getTitle());
    }

    //Post: activates the timer that controls when the alarm sounds.
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
