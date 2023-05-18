package org.file_handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.calendar.appointment.Appointment;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonFileHandlerStrategy implements FileHandlerStrategy {


    //Pre: File must be in JSON format.
    //Post: @inheritDoc
    @Override
    public void saveData(List<Appointment> data) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String json = objectMapper.writeValueAsString(data);
            System.out.println(json);
        } catch (JsonProcessingException ex) {
            System.out.println(ex);
            return;
        }

    }

    //Pre: File must be in JSON format.
    //Post: @inheritDoc
    @Override
    public List<Appointment> readData(){
        var data = new ArrayList<Appointment>();

        return data;
    }

}