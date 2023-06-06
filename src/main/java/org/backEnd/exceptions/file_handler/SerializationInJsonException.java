package org.backEnd.exceptions.file_handler;

import com.fasterxml.jackson.core.JsonProcessingException;
public class SerializationInJsonException extends JsonProcessingException {
    public SerializationInJsonException(String message){
        super(message);
    }
}
