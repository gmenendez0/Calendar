package org.viewsJavaFX;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewMessage {
    @FXML
    private Stage messageStage = new Stage();
    public void createMessage(String color, String message){
        Pane paneMessage = new Pane();
        paneMessage.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        paneMessage.setMinSize(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        paneMessage.setPrefSize(400.0, 50.0);
        paneMessage.setStyle("-fx-background-color: "+ color +";");

        Label labelError = new Label();
        labelError.setLayoutX(13.0);
        labelError.setLayoutY(13.0);
        labelError.setPrefSize(400.0, 21.0);
        labelError.setText(message);
        labelError.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        Font font = new Font("Impact", 16.0);
        labelError.setFont(font);

        paneMessage.getChildren().add(labelError);

        messageStage.setScene(new Scene(paneMessage));
        messageStage.show();
    }
}
