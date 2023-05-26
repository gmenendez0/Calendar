package org.file_handler;
import org.calendar.appointment.Appointment;
import org.file_handler.exceptions.SerializationInJsonException;

import java.io.*;
import java.util.List;

public class FileHandler {
    private final FileHandlerStrategy fileHandlerStrategy;
    public FileHandler(FileHandlerStrategy fileHandlerStrategy){
        this.fileHandlerStrategy = fileHandlerStrategy;
    }

    //Post: Saves given data in given file.
    public void save(List<Appointment> data, String path) throws IOException {
        try {
            BufferedOutputStream obj  = new BufferedOutputStream(new FileOutputStream(path));
            fileHandlerStrategy.saveData(data, obj);
            obj.close();
        } catch (FileNotFoundException | SerializationInJsonException ex) {
            throw ex;
        }
    }

    //Post: Reads given file and returns read data.
    public List<Appointment> load(String path) throws IOException{
        try {
            BufferedInputStream obj = new BufferedInputStream(new FileInputStream(path));
            List<Appointment> arrAppointment = fileHandlerStrategy.loadData(obj);
            obj.close();
            return arrAppointment;
        } catch (FileNotFoundException | SerializationInJsonException ex) {
            throw ex;
        }
    }
}