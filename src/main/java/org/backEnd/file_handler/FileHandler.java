package org.backEnd.file_handler;
import org.backEnd.calendar.appointment.Appointment;

import java.io.*;
import java.util.List;

public class FileHandler {
    private final FileHandlerStrategy fileHandlerStrategy;
    public FileHandler(FileHandlerStrategy fileHandlerStrategy){
        this.fileHandlerStrategy = fileHandlerStrategy;
    }

    //Post: Saves given data in given file.
    public void save(List<Appointment> data, String path) throws IOException {
        BufferedOutputStream obj  = new BufferedOutputStream(new FileOutputStream(path));
        fileHandlerStrategy.saveData(data, obj);
        obj.close();
    }

    //Post: Reads given file and returns read data.
    public List<Appointment> load(String path) throws IOException {
        BufferedInputStream obj = new BufferedInputStream(new FileInputStream(path));
        List<Appointment> arrAppointment = fileHandlerStrategy.loadData(obj);
        obj.close();
        return arrAppointment;
    }
}