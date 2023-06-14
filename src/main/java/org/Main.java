package org;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controllers.createAppointmentControllers;
import org.controllers.AppointmentDetailsControllers;
import org.controllers.HomeControllers;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.models.calendar.Calendar;
import org.models.file_handler.FileHandler;
import org.models.file_handler.JsonFileHandlerStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Main extends Application {

    private Calendar calendar;

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException{
        Parent root;
        calendar = new Calendar();

        initializeCalendar(calendar);

        var detailsController = new AppointmentDetailsControllers();
        var createControllers = new createAppointmentControllers();
        var homeController = new HomeControllers(calendar, detailsController, createControllers);
        root = homeController.getHomeRoot();

        setUpStage(root, stage);
    }

    @Override
    public void stop() throws Exception{
        closeCalendar(calendar);
        super.stop();
    }

    private void setUpStage(Parent root, Stage stage){
        Scene scene = new Scene(root, 960, 540);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Calendar");
        stage.show();
    }

    //Post: initialize the calendar with the appointments saved in the file.
    private void initializeCalendar(Calendar calendar) throws IOException{
        var objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        var jsonFileHandler = new JsonFileHandlerStrategy(objectMapper);
        var fileHandler = new FileHandler(jsonFileHandler);

        calendar.recoverAppointments(fileHandler, "src/main/resources/appointment.json");
    }

    //Post: Calls method to save the appointments in the file and closes calendar.
    private void closeCalendar(Calendar calendar) throws IOException{
        var objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        var jsonFileHandler = new JsonFileHandlerStrategy(objectMapper);
        var fileHandler = new FileHandler(jsonFileHandler);

        calendar.saveAppointments(fileHandler, "src/main/resources/appointment.json");
    }
}