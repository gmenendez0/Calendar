package org.calendar.visitor;

import org.calendar.appointment.Appointment;
import org.calendar.event.PeriodTimeEvent;
import org.calendar.event.WholeDayEvent;
import org.calendar.task.ExpirationTimeTask;
import org.calendar.task.WholeDayTask;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AppointmentsVisitor implements Visitor {
    //@inheritDoc
    @Override
    public void visitPeriodTimeEvent(PeriodTimeEvent periodTimeEvent, LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
        var selectedAppointments = new ArrayList<Appointment>();
        System.out.println("Visiting...");

        //return selectedAppointments;
    }

    //@inheritDoc
    @Override
    public void visitWholeDayEvent(WholeDayEvent wholeDayEvent, LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
        var selectedAppointments = new ArrayList<Appointment>();
        System.out.println("Visiting...");

        //return selectedAppointments;
    }

    //Pre: firstDateTime must be before secondDateTime.
    //Post: Returns true if date received is between firstDateTime and secondDateTime or if it is equal to any of them, returns false otherwise.
    private boolean dateIsBetweenDates(LocalDateTime dateTime, LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        return (dateTime.isEqual(firstDateTime)) || (dateTime.isAfter(firstDateTime) && dateTime.isBefore(secondDateTime)) || (dateTime.isEqual(secondDateTime));
    }

    //@inheritDoc
    @Override
    public void visitWholeDayTask(WholeDayTask wholeDayTask, LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
        var selectedAppointments = new ArrayList<Appointment>();
        var taskStartDateTime = wholeDayTask.getStartDateTime();

        if(dateIsBetweenDates(taskStartDateTime, firstDateTime, secondDateTime)) selectedAppointments.add(wholeDayTask);

        //return selectedAppointments;
    }
    
    //@inheritDoc
    @Override
    public void visitExpirationTimeTask(ExpirationTimeTask expirationTimeTask, LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
        var selectedAppointments = new ArrayList<Appointment>();
        var taskExpirationDateTime = expirationTimeTask.getExpirationDateTime();

        if(dateIsBetweenDates(taskExpirationDateTime, firstDateTime, secondDateTime)) selectedAppointments.add(expirationTimeTask);

        //return selectedAppointments;
    }
}
