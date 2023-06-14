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

public class NotificationAlarmsControllers {
    private final int TIME = 60000;
    private Timer timer = new Timer();
    @FXML
    private final Stage notificationStage = new Stage();
    @FXML
    private Pane paneNotification;
    @FXML
    private Label labelNotification;
    @FXML
    private Pane paneError;
    @FXML
    private Label labelError;


    private Scene configNotificationStage(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        fxmlLoader.setController(this);
        return new Scene(fxmlLoader.load());
    }

    private void toRing(){}
    private void sendEmail(){}
    private void showNotificationAlarm(Calendar calendar, int id) throws IOException {
        Appointment appointmentActive = calendar.getAppointment(id);
        Scene notificationScene = configNotificationStage("/views/appointmentCreate.fxml");
        labelNotification.setText("It's " + appointmentActive.getType() + " time: " + appointmentActive.getTitle());
        notificationStage.setScene(notificationScene);
        notificationStage.show();
    }

    public void activeNotification(Alarm alarm, Calendar calendar, int id){
        TimerTask timerTask = new TimerTask() {
        @Override
            public void run() {
                try {
                    showNotificationAlarm(calendar, id);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule(timerTask, 0, TIME);
    }

    public void error(String message) throws IOException {
        Scene notificationScene = configNotificationStage("/views/error.fxml");
        labelError.setText(message);
        notificationStage.setScene(notificationScene);
        notificationStage.show();
    }

}
