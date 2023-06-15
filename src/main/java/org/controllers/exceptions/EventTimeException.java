package org.controllers.exceptions;

public class EventTimeException extends Exception{

    //Fired at the moment the start of the event exceeds the end
    public EventTimeException(String message) {
        super(message);
    }
}
