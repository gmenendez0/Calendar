package org.file_handler;
import org.calendar.appointment.Appointment;

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
        } catch (IOException ex) {
            throw ex;
        }
    }

    //Post: Reads given file and returns read data.
    public List<Appointment> read(String path) throws IOException{
        try {
            BufferedInputStream obj = new BufferedInputStream(new FileInputStream(path));
            List<Appointment> arrAppointment = fileHandlerStrategy.readData(obj);
            obj.close();
            return arrAppointment;
        } catch (IOException ex) {
            throw ex;
        }
    }
}