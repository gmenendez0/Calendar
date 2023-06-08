package org;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controllers.HomeControllers;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.models.calendar.Calendar;
import org.models.calendar.event.PeriodTimeEvent;
import org.models.calendar.event.WholeDayEvent;
import org.models.calendar.task.ExpirationTimeTask;
import org.models.calendar.task.WholeDayTask;
import org.models.file_handler.FileHandler;
import org.models.file_handler.JsonFileHandlerStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

public class Main extends Application {
    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException{
        Parent root;
        var calendar = new Calendar();

        //initializeCalendar(calendar);
        fillCalendar(calendar);

        var homeController = new HomeControllers(calendar);
        root = homeController.getHomeRoot();

        setUpStage(root, stage);
    }

    @Override
    public void stop() throws Exception{
        //closeCalendar(calendar);
        super.stop();
    }

    //? FOR TESTING PURPOSES ONLY
    private void fillCalendar(Calendar calendar){
        //? FOR TESTING PURPOSES ONLY
        var periodTimeEvent = new PeriodTimeEvent("periodTimeEvent ", "desc", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        var wholeDayEvent = new WholeDayEvent("wholeDayEvent", "desc", LocalDateTime.now().toLocalDate());
        var wholeDayTask = new WholeDayTask("wholeDayTask", "desc", LocalDateTime.now().toLocalDate());
        var expirationTimeTask = new ExpirationTimeTask("expirationTimeTask", "desc", LocalDateTime.now().plusDays(0));
        calendar.addAppointment(periodTimeEvent);
        calendar.addAppointment(wholeDayEvent);
        calendar.addAppointment(wholeDayTask);
        calendar.addAppointment(expirationTimeTask);
        //? FOR TESTING PURPOSES ONLY
    }

    private void setUpStage(Parent root, Stage stage){
        Scene scene = new Scene(root, 1920, 1080);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Calendar");
        stage.show();
    }

    //Post: initialize the calendar with the appointments saved in the file.
    private void initializeCalendar(Calendar calendar) throws IOException{
        var objectMapper = new ObjectMapper();
        var jsonFileHandler = new JsonFileHandlerStrategy(objectMapper);
        var fileHandler = new FileHandler(jsonFileHandler);

        calendar.recoverAppointments(fileHandler, "src/main/resources/appointment.json");
    }

    //Post: Calls method to save the appointments in the file and closes calendar.
    private void closeCalendar(Calendar calendar) throws IOException{
        var objectMapper = new ObjectMapper();
        var jsonFileHandler = new JsonFileHandlerStrategy(objectMapper);
        var fileHandler = new FileHandler(jsonFileHandler);

        calendar.saveAppointments(fileHandler, "src/main/resources/appointment.json");
    }
}