package org.file_handler.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
public class SerializationInJsonException extends JsonProcessingException {
    public SerializationInJsonException(String message){
        super(message);
    }
}
