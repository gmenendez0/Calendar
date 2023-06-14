package org;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controllers.CreateAppointmentControllers;
import org.controllers.AppointmentDetailsControllers;
import org.controllers.HomeControllers;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.models.calendar.Calendar;
import org.models.file_handler.FileHandler;
import org.models.file_handler.JsonFileHandlerStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public class Main extends Application {
    final int WIDTH_RESOLUTION = 1920;

    final int HEIGHT_RESOLUTION = 1080;

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
        var createControllers = new CreateAppointmentControllers();
        var homeController = new HomeControllers(calendar, detailsController, createControllers);
        root = homeController.getHomeRoot();

        setUpStage(root, stage);
    }

    @Override
    public void stop() throws Exception{
        closeCalendar(calendar);
        super.stop();
    }

    //Post: Set up the stage.
    private void setUpStage(Parent root, Stage stage){
        Scene scene = new Scene(root, WIDTH_RESOLUTION, HEIGHT_RESOLUTION);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Calendar");
        stage.show();
    }

    //Post: Initializes calendar with saved appointments.
    private void initializeCalendar(Calendar calendar) throws IOException{
        var objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        var jsonFileHandler = new JsonFileHandlerStrategy(objectMapper);
        var fileHandler = new FileHandler(jsonFileHandler);

        calendar.recoverAppointments(fileHandler, "src/main/resources/appointment.json");
    }

    //Post: Calls method to save the appointments and closes calendar.
    private void closeCalendar(Calendar calendar) throws IOException{
        var objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        var jsonFileHandler = new JsonFileHandlerStrategy(objectMapper);
        var fileHandler = new FileHandler(jsonFileHandler);

        calendar.saveAppointments(fileHandler, "src/main/resources/appointment.json");
    }
}