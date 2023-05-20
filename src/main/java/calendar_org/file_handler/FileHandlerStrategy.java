package calendar_org.file_handler;
import calendar_org.calendar.appointment.Appointment;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public interface FileHandlerStrategy extends Serializable {
    //Post: Saves given data in file.
    void saveData(List<Appointment> data, String path, ObjectMapper objectMapper, BufferedOutputStream obj);

    //Post: Reads given file and returns read data.
    List<Appointment> readData(ObjectMapper objectMapper, BufferedInputStream obj, String path);
}
