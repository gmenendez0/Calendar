package org.calendar.visitor;

import org.calendar.appointment.Appointment;
import org.calendar.event.PeriodTimeEvent;
import org.calendar.event.WholeDayEvent;
import org.calendar.task.ExpirationTimeTask;
import org.calendar.task.WholeDayTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsVisitor implements Visitor {
    //Pre: eventStartTime and firstDateTime must be before eventEndingTime and secondDateTime respectively.
    //Post: Returns true if startDateTime is between the given dates (or equal firstDateTime) and if endingDateTime is before the given dates (or equal to secondDateTime). False otherwise.
    private boolean eventIsBetweenDates(LocalDateTime eventStartTime, LocalDateTime eventEndingTime, LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        return (eventStartTime.isAfter(firstDateTime) || eventStartTime.isEqual(firstDateTime)) && (eventEndingTime.isBefore(secondDateTime) || eventEndingTime.isEqual(secondDateTime));
    }

    //Pre: eventStartTime and firstDateTime must be before eventEndingTime and secondDateTime respectively.
    //Post: Returns true if event startDateTime is before firstDateTime and endingDateTime is between the given dates (or equal to any of them). False otherwise.
    private boolean eventEndsBetweenDates(LocalDateTime eventStartTime, LocalDateTime eventEndingTime, LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        return (eventStartTime.isBefore(firstDateTime) && (eventEndingTime.isAfter(firstDateTime) || eventEndingTime.isEqual(firstDateTime)) &&
                (eventEndingTime.isBefore(secondDateTime) || eventEndingTime.isEqual(secondDateTime)));
    }

    //Pre: eventStartTime and firstDateTime must be before eventEndingTime and secondDateTime respectively.
    //Post: Returns true if the event starts between dates given (or is equal to any of them) and finishes after. False otherwise.
    private boolean eventStartsBetweenDates(LocalDateTime eventStartTime, LocalDateTime eventEndingTime, LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        return (((eventStartTime.isEqual(firstDateTime) || eventStartTime.isAfter(firstDateTime)) && eventStartTime.isBefore(secondDateTime)) && eventEndingTime.isAfter(secondDateTime));
    }

    //Pre: eventStartTime and firstDateTime must be before eventEndingTime and secondDateTime respectively.
    //Post: Returns true if the event starts before the firstDatetime and ends after the secondDateTime given.
    private boolean eventStartsBeforeAndEndsAfterDates(LocalDateTime eventStartTime, LocalDateTime eventEndingTime, LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        return (eventStartTime.isBefore(firstDateTime) && eventEndingTime.isAfter(secondDateTime));
    }

    //Pre: eventStartTime and firstDateTime must be before eventEndingTime and secondDateTime respectively.
    //Post: Returns true if event is taking place between the two dates given. False otherwise.
    private boolean eventTakesPlacesBetweenDates(LocalDateTime eventStartTime, LocalDateTime eventEndingTime, LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        return eventIsBetweenDates(eventStartTime, eventEndingTime, firstDateTime, secondDateTime) ||
               eventEndsBetweenDates(eventStartTime, eventEndingTime, firstDateTime, secondDateTime) ||
               eventStartsBetweenDates(eventStartTime, eventEndingTime, firstDateTime, secondDateTime) ||
               eventStartsBeforeAndEndsAfterDates(eventStartTime, eventEndingTime, firstDateTime, secondDateTime);
    }

    //Pre: Given event must repeat.
    //Post: Checks if any of the event s repetitions fit between given dates. If so, adds them to selectedAppointments.
    private void checkRepetitions(PeriodTimeEvent periodTimeEvent, List<Appointment> selectedAppointments, LocalDateTime firstDateTime, LocalDateTime secondDateTime){

    }

    //@inheritDoc
    @Override
    public void visitPeriodTimeEvent(PeriodTimeEvent periodTimeEvent, LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
        var selectedAppointments = new ArrayList<Appointment>();
        var eventStartTime = periodTimeEvent.getStartDateTime();
        var eventEndingTime = periodTimeEvent.getEndingDateTime();

        if(eventTakesPlacesBetweenDates(eventStartTime, eventEndingTime, firstDateTime, secondDateTime)) selectedAppointments.add(periodTimeEvent);

        if(periodTimeEvent.IsRepeated()) checkRepetitions(periodTimeEvent, selectedAppointments, firstDateTime, secondDateTime);

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
