package file_handler;
import org.calendar.appointment.Appointment;

import java.util.List;

public interface FileHandlerStrategy{

    //Post: Saves given data in file.
    void saveData(List<Appointment> data);

    //Post: Reads given file and returns read data.
    List<Appointment> readData();
}
