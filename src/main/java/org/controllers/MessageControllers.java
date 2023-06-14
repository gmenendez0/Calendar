package org.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MessageControllers {
    @FXML
    private Stage notificationStage = new Stage();

    private Scene configNotificationStage(String message, String color) {
        Pane paneError = new Pane();
        paneError.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        paneError.setMinSize(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        paneError.setPrefSize(335.0, 47.0);
        paneError.setStyle("-fx-background-color: "+ color +";");

        Label labelError = new Label();
        labelError.setLayoutX(13.0);
        labelError.setLayoutY(13.0);
        labelError.setPrefSize(350.0, 21.0);
        labelError.setText(message);
        labelError.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        Font font = new Font("Impact", 16.0);
        labelError.setFont(font);

        paneError.getChildren().add(labelError);
        return new Scene(paneError);
    }

    public void error(String message){
        Scene notificationScene = configNotificationStage(message, "#c7433a");
        notificationStage.setScene(notificationScene);
        notificationStage.show();
    }

    public void success(String message){
        Scene notificationScene = configNotificationStage(message, "#50e336");
        notificationStage.setScene(notificationScene);
        notificationStage.show();
    }
}
