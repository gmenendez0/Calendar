package org.models.calendar.visitor;

import org.models.calendar.event.Event;
import org.models.calendar.event.WholeDayEvent;
import org.models.calendar.appointment.Appointment;
import org.models.calendar.event.PeriodTimeEvent;
import org.models.calendar.event.frequency.Frequency;
import org.models.calendar.task.ExpirationTimeTask;
import org.models.calendar.task.WholeDayTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsVisitor implements Visitor {
    final int HUGE_YEAR = 20000;
    final int JANUARY = 1;
    final int FIRST = 1;
    final int MID_DAY_TIME = 12;
    final int MINUTES = 0;

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
        return (((eventStartTime.isEqual(firstDateTime) || eventStartTime.isAfter(firstDateTime)) && (eventStartTime.isBefore(secondDateTime)) || eventStartTime.isEqual(secondDateTime)) && eventEndingTime.isAfter(secondDateTime));
    }

    //Pre: eventStartTime and firstDateTime must be before eventEndingTime and secondDateTime respectively.
    //Post: Returns true if the event starts before the firstDatetime and ends after the secondDateTime given.
    private boolean eventStartsBeforeAndEndsAfterDates(LocalDateTime eventStartTime, LocalDateTime eventEndingTime, LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        return (eventStartTime.isBefore(firstDateTime) && eventEndingTime.isAfter(secondDateTime));
    }

    //Pre: eventStartTime and firstDateTime must be before eventEndingTime and secondDateTime respectively.
    //Post: Returns true if event is taking place between the two dates given. False otherwise.
    //Important: firstDateTime and secondDateTime are included in the given range!
    private boolean eventTakesPlaceBetweenDates(LocalDateTime eventStartTime, LocalDateTime eventEndingTime, LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        return eventIsBetweenDates(eventStartTime, eventEndingTime, firstDateTime, secondDateTime) ||
               eventEndsBetweenDates(eventStartTime, eventEndingTime, firstDateTime, secondDateTime) ||
               eventStartsBetweenDates(eventStartTime, eventEndingTime, firstDateTime, secondDateTime) ||
               eventStartsBeforeAndEndsAfterDates(eventStartTime, eventEndingTime, firstDateTime, secondDateTime);
    }

    //Post: Returns true in case repetition is evaluable, false otherwise.
    private boolean repetitionIsEvaluable(Event event, LocalDateTime latestCheckedEventStartDateTime, LocalDateTime latestCheckedEventEndingDateTime, LocalDateTime secondDateTime){
        return event.thereIsNextRepetition(latestCheckedEventEndingDateTime) && (latestCheckedEventStartDateTime.isBefore(secondDateTime) || latestCheckedEventStartDateTime.isEqual(secondDateTime));
    }

    //Post: Adds all event s repetitions that take place between firstDateTime and secondDateTime to selectedAppointments.
    //Important: "take place between" is defined at eventTakesPlaceBetweenDates post-conditions.
    private void selectAppropriateRepetitions(Event event, List<Appointment> selectedAppointments, LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        var latestCheckedEventStartDateTime = event.getStartDateTime();
        var latestCheckedEventEndingDateTime = event.getEndingDateTime();

        while(repetitionIsEvaluable(event, latestCheckedEventStartDateTime, latestCheckedEventEndingDateTime, secondDateTime)){
            latestCheckedEventStartDateTime = event.getNextRepetitionStartDateTime(latestCheckedEventEndingDateTime);
            latestCheckedEventEndingDateTime = event.getNextRepetitionEndingDateTime(latestCheckedEventEndingDateTime);

            if(eventTakesPlaceBetweenDates(latestCheckedEventStartDateTime, latestCheckedEventEndingDateTime, firstDateTime, secondDateTime)){
               selectedAppointments.add(event.getNextRepetition());
            }

            event = event.getNextRepetition();
        }
    }

    //Pre: Given event must repeat.
    //Post: Checks if any of the event s repetitions fit between given dates. If so, adds them to selectedAppointments.
    private void checkRepetitions(Event event, List<Appointment> selectedAppointments, LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        var originalEventStartDateTime = event.getStartDateTime();
        var lastRepetitionEndingDateTime = event.getLastRepetitionEndingDateTime();

        if(lastRepetitionEndingDateTime == null) lastRepetitionEndingDateTime = LocalDateTime.of(HUGE_YEAR, JANUARY, FIRST, MID_DAY_TIME, MINUTES);

        if(eventTakesPlaceBetweenDates(originalEventStartDateTime, lastRepetitionEndingDateTime, firstDateTime, secondDateTime)){
            selectAppropriateRepetitions(event, selectedAppointments, firstDateTime, secondDateTime);
        }
    }

    //Pre: firstDateTime must be before secondDateTime.
    //Post: Returns a list with all the events derived from the received event that take place between the received dates.
    private List<Appointment> visitEvent(Event event, LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        var selectedAppointments = new ArrayList<Appointment>();
        var eventStartTime = event.getStartDateTime();
        var eventEndingTime = event.getEndingDateTime();

        if(eventTakesPlaceBetweenDates(eventStartTime, eventEndingTime, firstDateTime, secondDateTime)) selectedAppointments.add(event);
        if(event.isRepeated()) checkRepetitions(event, selectedAppointments, firstDateTime, secondDateTime);

        return selectedAppointments;
    }

    //@inheritDoc
    @Override
    public List<Appointment> visitPeriodTimeEvent(PeriodTimeEvent periodTimeEvent, LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
        return visitEvent(periodTimeEvent, firstDateTime, secondDateTime);
    }

    //@inheritDoc
    @Override
    public List<Appointment> visitWholeDayEvent(WholeDayEvent wholeDayEvent, LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
        return visitEvent(wholeDayEvent, firstDateTime, secondDateTime);
    }

    //Pre: firstDateTime must be before secondDateTime.
    //Post: Returns true if date received is between firstDateTime and secondDateTime or if it is equal to any of them, returns false otherwise.
    private boolean dateIsBetweenDates(LocalDateTime dateTime, LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        return (dateTime.isEqual(firstDateTime)) || (dateTime.isAfter(firstDateTime) && dateTime.isBefore(secondDateTime)) || (dateTime.isEqual(secondDateTime));
    }

    //@inheritDoc
    @Override
    public List<Appointment> visitWholeDayTask(WholeDayTask wholeDayTask, LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
        var selectedAppointments = new ArrayList<Appointment>();
        var taskStartDateTime = wholeDayTask.getStartDateTime();

        if(dateIsBetweenDates(taskStartDateTime, firstDateTime, secondDateTime)) selectedAppointments.add(wholeDayTask);

        return selectedAppointments;
    }
    
    //@inheritDoc
    @Override
    public List<Appointment> visitExpirationTimeTask(ExpirationTimeTask expirationTimeTask, LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
        var selectedAppointments = new ArrayList<Appointment>();
        var taskExpirationDateTime = expirationTimeTask.getExpirationDateTime();

        if(dateIsBetweenDates(taskExpirationDateTime, firstDateTime, secondDateTime)) selectedAppointments.add(expirationTimeTask);

        return selectedAppointments;
    }

}
