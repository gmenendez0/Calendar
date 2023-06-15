package org.controllers;

import org.views.Message;

public class MessageControllers {
    private final Message message = new Message();

    //Post: Configs the message with the given color and string.
    private void configMessageStage(String color, String message) {
        this.message.createMessage(color, message);
    }

    //Post: Sets up the error message
    public void error(String message){
        configMessageStage("#c7433a", message);
    }

    //Post: Sets up the success message
    public void success(String message){
        configMessageStage("#50e336", message);
    }

    //Post: Sets up the warning message
    public void warningFile(String message) {
        configMessageStage("#f0ce37", message);
    }
}
