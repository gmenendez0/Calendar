package org.file_handler;

import org.calendar.appointment.Appointment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonFileHandlerStrategy implements FileHandlerStrategy {
    private final ObjectMapper objectMapper;

    public JsonFileHandlerStrategy(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    //Pre: receives a list of appointments that will be output to a '.json' file represented by the other BufferedOutputStream parameter.
    //Post: the file with the class records is created or edited.
    @Override
    public void saveData(List<Appointment> data, BufferedOutputStream obj) throws IOException {
        try {
            objectMapper.writeValue(obj, data);
        } catch (JsonProcessingException ex) {
            throw ex;
        } catch (IOException ex) {
            throw ex;
        }
    }

    //Pre: receives the '.json' file represented by the other BufferedOutputStream parameter.
    //Post: returns a list of appointments from the '.json' file.
    @Override
    public List<Appointment> readData(BufferedInputStream obj) throws IOException {
        var data = new ArrayList<Appointment>();
        try{
            data = objectMapper.readValue(obj, new TypeReference<>() {});
            return data;
        } catch (Exception ex){
            throw ex;
        }
    }
}