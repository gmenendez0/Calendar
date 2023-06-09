package org.models.file_handler;
import org.models.calendar.appointment.Appointment;

import java.io.*;
import java.util.List;

public interface FileHandlerStrategy extends Serializable {
    //Post: Saves given data in file.
    void saveData(List<Appointment> data, BufferedOutputStream obj) throws IOException;

    //Post: Reads given file and returns read data.
    List<Appointment> loadData(BufferedInputStream obj) throws IOException;
}
