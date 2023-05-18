package org.file_handler;
import org.calendar.appointment.Appointment;

import javax.json.JsonArray;
import java.util.List;

public class FileHandler {
    private final FileHandlerStrategy fileHandlerStrategy;

    public FileHandler(FileHandlerStrategy fileHandlerStrategy){
        this.fileHandlerStrategy = fileHandlerStrategy;
    }

    //Post: Saves given data in given file.
    public void save(List<Appointment> data, String path){
        fileHandlerStrategy.saveData(data);


    }

    //Post: Reads given file and returns read data.
    public List<Appointment> read(String path){
        return fileHandlerStrategy.readData();
    }
}