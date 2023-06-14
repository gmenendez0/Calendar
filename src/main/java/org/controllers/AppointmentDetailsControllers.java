package org.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.models.calendar.Calendar;
import org.models.calendar.alarms.Alarm;
import org.models.calendar.appointment.Appointment;
import org.models.calendar.visitor.appointment_details.AppointmentDetailsVisitorImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentDetailsControllers {

    private final Stage detailsStage = new Stage();

    private final AppointmentDetailsVisitorImpl visitor = new AppointmentDetailsVisitorImpl();

    @FXML
    private Text titleFrequencyAppointment;
    @FXML
    private Text frequencyAppointment;
    @FXML
    private Label titleWindow;
    @FXML
    private Text titleAppointment;
    @FXML
    private Text descriptionAppointment;
    @FXML
    private Text typeAppointment;
    @FXML
    private Text dateImportantAppointment;
    @FXML
    private Text alarmsListAppointment;

    public void setUpViewDetailsConfig(Integer id, LocalDateTime start, LocalDateTime end, Calendar calendar) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/appointmentDetails.fxml"));
        fxmlLoader.setController(this);
        Scene secondScene = new Scene(fxmlLoader.load());
        secondScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        detailsStage.setScene(secondScene);

        completeDetailsStage(calendar.getAppointmentsBetween(start, end).get(id));
    }

    private String writeAlarms(Appointment appointment){
        List<Alarm> listAlarm = appointment.getAlarms();
        if(listAlarm.isEmpty()) return "This " + appointment.getType() + " has not alarms.";
        return listAlarm.stream().map(Object::toString).reduce("", (a, b) -> a + " " + b + "\n");
    }

    private void addFrequencyInDetails(Appointment appointment){
        if(appointment.getType().equals("Event")) titleFrequencyAppointment.setText("Frequency");
        frequencyAppointment.setText(appointment.acceptVisitorDetailsFrequency(visitor));
    }

    private void completeDetailsStage(Appointment appointment){
        titleWindow.setText(appointment.getTitle());
        titleAppointment.setText(appointment.getTitle());
        descriptionAppointment.setText(appointment.getDescription());
        typeAppointment.setText(appointment.getType());
        dateImportantAppointment.setText(appointment.acceptVisitorDetailsDates(visitor));
        alarmsListAppointment.setText(writeAlarms(appointment));
        addFrequencyInDetails(appointment);
        detailsStage.show();
    }
}
