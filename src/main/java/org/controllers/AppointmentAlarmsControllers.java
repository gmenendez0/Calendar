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
    public void activateTimer(Alarm alarm, Appointment appointment){
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                boolean isTimeToRing = alarm.checkTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
                if(isTimeToRing) {
                    showNotificationAlarm(appointment);
                    stop();
                }
            }
        };
        animationTimer.start();
    }
}
