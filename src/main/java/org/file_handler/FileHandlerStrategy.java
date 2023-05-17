package org.file_handler;
import org.calendar.appointment.Appointment;

import java.io.Serializable;
import java.util.List;

public interface FileHandlerStrategy extends Serializable {
    //Post: Saves given data in file.
    void saveData(List<Appointment> data);

    //Post: Reads given file and returns read data.
    List<Appointment> readData();
}
