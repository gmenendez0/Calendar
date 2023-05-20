package calendar_org.file_handler;

import calendar_org.calendar.appointment.Appointment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonFileHandlerStrategy implements FileHandlerStrategy {


    //Pre: File must be in JSON format.
    //Post: @inheritDoc
    @Override
    public void saveData(List<Appointment> data, String path, ObjectMapper objectMapper, BufferedOutputStream obj) {
        try {
//            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            objectMapper.writeValue(obj, data);
        } catch (JsonProcessingException ex) {
            return;
        } catch (IOException ex) {
            return;
        }
    }

    //Pre: File must be in JSON format.
    //Post: @inheritDoc
    @Override
    public List<Appointment> readData(ObjectMapper objectMapper, BufferedInputStream obj, String path){
        var data = new ArrayList<Appointment>();
        try{
            data = objectMapper.readValue(obj, new TypeReference<ArrayList<Appointment>>() {});
            return data;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

}