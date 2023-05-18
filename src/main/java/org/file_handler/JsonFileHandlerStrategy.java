package org.file_handler;

import org.calendar.appointment.Appointment;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonFileHandlerStrategy implements FileHandlerStrategy {

    private JsonObject createJsonObject(Map appData){







        return null;
    }


    //Pre: File must be in JSON format.
    //Post: @inheritDoc
    @Override
    public void saveData(List<Appointment> data){

    }

    //Pre: File must be in JSON format.
    //Post: @inheritDoc
    @Override
    public List<Appointment> readData(){
        var data = new ArrayList<Appointment>();

        return data;
    }

}