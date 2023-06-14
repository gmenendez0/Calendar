package org.controllers;

import org.views.ViewMessage;

public class MessageControllers {
    private final ViewMessage viewMessage = new ViewMessage();

    private void configMessageStage(String color, String message) {
        viewMessage.createMessage(color, message);
    }

    public void error(String message){
        configMessageStage("#c7433a", message);
    }

    public void success(String message){
        configMessageStage("#50e336", message);
    }

    public void warningFile(String message) {
        configMessageStage("#f0ce37", message);
    }
}
