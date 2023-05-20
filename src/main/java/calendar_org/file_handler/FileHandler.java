package calendar_org.file_handler;
import calendar_org.calendar.appointment.Appointment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private final FileHandlerStrategy fileHandlerStrategy;
    public FileHandler(FileHandlerStrategy fileHandlerStrategy){
        this.fileHandlerStrategy = fileHandlerStrategy;
    }

    //Post: Saves given data in given file.
    public void save(List<Appointment> data, String path){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            BufferedOutputStream obj  = new BufferedOutputStream(
                    new FileOutputStream(path)
            );
            fileHandlerStrategy.saveData(data, path, objectMapper, obj);
            obj.close();
        } catch (IOException ex) {
            return;
        }
    }

    //Post: Reads given file and returns read data.
    public List<Appointment> read(String path){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            BufferedInputStream obj = new BufferedInputStream(new FileInputStream(path));
            List<Appointment> arrAppointment = fileHandlerStrategy.readData(objectMapper, obj, path);
            obj.close();
            return arrAppointment;
        } catch (IOException ex) {
            return null;
        }
    }
}