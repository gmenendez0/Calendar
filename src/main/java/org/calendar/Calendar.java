package org.calendar;

import org.calendar.alarms.Alarm;
import org.calendar.appointment.Appointment;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    final int INVALID_ID = -1;
    final int EMPTY = 0;

    private final List<Appointment> appointments = new ArrayList<>();

    public Calendar(){}

    //Post: Adds an appointment to the appointments list.
    public void addAppointment(Appointment appointment){
        appointment.setId(appointments.size());
        appointments.add(appointment);
    }

    //Pre: ID Must not be a negative number.
    //Post: Returns the appointment with the id received.
    public Appointment getAppointment(int id){
        return appointments.get(id);
    }

    //Pre: ID Must not be a negative number.
    //Post: Destroys the appointment with the given ID. Returns true in case of success, false otherwise.
    public boolean destroyAppointment(int id){
        if(idNotFound(id, appointments.size())) return false;

        appointments.get(id).destroy();
        return true;
    }

    //Post: Returns true if there is not an appointment with the ID received, false otherwise.
    private boolean idNotFound(int id, int appointmentsSize){
        return (id <= INVALID_ID || appointmentsSize == EMPTY  || appointments.size() < id);
    }

    //Pre: ID Must not be a negative number.
    //Post: Replaces the appointment with the given ID, with the appointment received. Returns true in case of success, false otherwise.
    public boolean editAppointment(int id, Appointment appointment){
        if(idNotFound(id, appointments.size())) return false;

        appointment.setId(id);
        appointments.set(id, appointment);
        return true;
    }

    //Pre: ID Must not be a negative number.
    //Post: Adds an alarm to the appointment with the given id.
    public boolean addAlarmToAppointment(int id, Alarm alarm){
        if(idNotFound(id, appointments.size())) return false;

        getAppointment(id).addObserver(alarm);

        return true;
    }
}
