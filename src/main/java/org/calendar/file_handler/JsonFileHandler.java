package org.calendar.file_handler;

import org.calendar.appointment.Appointment;

import java.util.ArrayList;
import java.util.List;

public class JsonFileHandler implements FileHandlerStrategy{
    //Pre: File must be in JSON format.
    //Post: @inheritDoc
    @Override
    public void saveData(List<Appointment> data){
        //? Save data in file...
    }

    //Pre: File must be in JSON format.
    //Post: @inheritDoc
    @Override
    public List<Appointment> readData(){
        var data = new ArrayList<Appointment>();

        //? Read data from file...

        return data;
    }
}
