package org.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.models.calendar.Calendar;
import org.models.calendar.alarms.Alarm;
import org.models.calendar.appointment.Appointment;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class AppointmentAlarmsControllers {

    private final Timer timer = new Timer();
    private final MessageControllers messageControllers = new MessageControllers();

    @FXML
    private final Stage notificationStage = new Stage();
    @FXML
    private Pane paneNotification;
    @FXML
    private Label labelNotification;


    private Scene configNotificationStage() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/appointmentCreate.fxml"));
            fxmlLoader.setController(this);
            return new Scene(fxmlLoader.load());
        } catch (IOException ex) {
            messageControllers.error("Failed to import '.fxml' file.");
        }
        notificationStage.close();
        return null;
    }
    private void showNotificationAlarm(Calendar calendar, int id) {
        Appointment appointmentActive = calendar.getAppointment(id);
        Scene notificationScene = configNotificationStage();
        labelNotification.setText("It's " + appointmentActive.getType() + " time: " + appointmentActive.getTitle());
        notificationStage.setScene(notificationScene);
        notificationStage.show();
    }

    public void active(Alarm alarm, Calendar calendar){
        TimerTask timerTask = new TimerTask() {
        @Override
            public void run() {
                //if(alarm.update(LocalDateTime.now())) showNotificationAlarm(calendar, alarm.getId());
            }
        };
        final int TIME = 60000;
        timer.schedule(timerTask, 0, TIME);
    }
}
