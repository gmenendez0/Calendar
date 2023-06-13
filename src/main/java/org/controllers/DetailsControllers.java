package org.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.models.calendar.Calendar;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public class DetailsControllers {

    private Stage detailsStage = new Stage();
    private Calendar calendar;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/details.fxml"));
        fxmlLoader.setController(this);
        Scene secondScene = new Scene(fxmlLoader.load());
        secondScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        detailsStage.setScene(secondScene);

        completeDetailsStage(calendar.getAppointmentsBetween(start, end).get(id).dataToMapOfString());
    }

    private void addFrequencyInDetails(Map<String, String> data){
        if(data.get("Type").equals("Event")){
            titleFrequencyAppointment.setText("Frequency");
            frequencyAppointment.setText(data.get("Frequency"));
        }
    }

    private void completeDetailsStage(Map<String, String> data){
        titleWindow.setText(data.get("Title"));
        titleAppointment.setText(data.get("Title"));
        descriptionAppointment.setText(data.get("Description"));
        typeAppointment.setText(data.get("Type"));
        dateImportantAppointment.setText(data.get("DateImportant"));
        alarmsListAppointment.setText(data.get("Alarms"));
        addFrequencyInDetails(data);
        detailsStage.show();
    }
}
