package org.calendar;

import org.calendar.alarms.Alarm;
import org.calendar.appointment.Appointment;
import org.calendar.visitor.AppointmentsVisitor;
import org.file_handler.FileHandlerStrategy;

import java.time.LocalDateTime;
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

    //Pre:initialDateTime must be before finalDateTime
    //Post: Returns all non-destroyed appointments that take place at the same moment or after the inicialDateTime and before or at the same moment as the finalDateTime.
    //Example: To get all the appointments of 04/23, dates should be: inicialDateTime = 01/04/23 00:00:00 and finalDateTime = 31/04/23 23:59:59
    public List<Appointment> getAppointmentsBetween(LocalDateTime inicialDateTime, LocalDateTime finalDateTime){
        List<Appointment> selectedAppointments = new ArrayList<>();
        List<Appointment> visitorSelectedAppointments;
        var visitor = new AppointmentsVisitor();

        for (var appointment : appointments) {
            if(!appointment.getDestroyed()) {
                visitorSelectedAppointments = appointment.acceptVisitor(visitor, inicialDateTime, finalDateTime);
                selectedAppointments.addAll(visitorSelectedAppointments);
            }
        }

        return selectedAppointments;
    }

    //Pre: ID Must not be a negative number.
    //Post: Destroys the appointment with the given ID. Returns true in case of success, false otherwise.
    public boolean destroyAppointment(int id){
        if(idNotFound(id, appointments.size())) return false;

        appointments.get(id).setDestroyed();
        return true;
    }

    //Post: Returns true if there is not an appointment with the ID received, false otherwise.
    private boolean idNotFound(int id, int appointmentsSize){
        return (id <= INVALID_ID || appointmentsSize == EMPTY  || appointments.size() <= id);
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
    //Post: Adds an alarm to the appointment with the given id. Returns true in case of success, false otherwise.
    public boolean addAlarmToAppointment(int id, Alarm alarm){
        if(idNotFound(id, appointments.size())) return false;

        getAppointment(id).addAlarm(alarm);

        return true;
    }

    //Pre: ID and alarmId Must not be negative numbers.
    //Post: Removes the alarm with id = alarmId at the appointment with id = appointmentId. Returns true in case of success, false otherwise.
    public boolean removeAlarmFromAppointment(int appointmentId, int alarmId){
        if(idNotFound(appointmentId, appointments.size())) return false;

        return appointments.get(appointmentId).removeAlarm(alarmId);
    }
}
