package org;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import java.io.IOException;

public class Main extends Application{
    @FXML
    private ListView<Button> appointmentList;

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        appointmentList.getItems().add(new Button("Evento 1" + "   Fecha y hora de inicio: 01/01/23 12:00:00" + "   Fecha y hora de expiracion: 01/01/23 13:00:00" + "   Completado: No"));
        appointmentList.getItems().add(new Button("Evento 2" + "   Fecha: 01/01/23" + "   Completado: No"));
        appointmentList.getItems().add(new Button("Tarea 1 " +  "    Fecha y hora de expiracion: 01/01/23 12:30" + "   Completado: No"));
        appointmentList.getItems().add(new Button("Tarea 2 " +  "    Fecha de expiracion: 01/01/23" + "   Completado: No"));


        Scene scene = new Scene(root, 1920, 1080);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Calendar");
        stage.show();
    }
}