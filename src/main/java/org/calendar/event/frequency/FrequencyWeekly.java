package org.calendar.event.frequency;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FrequencyWeekly extends Frequency {
    private final ArrayList<DayOfWeek> weekDays;
    final int NUMBER_OF_DAYS = 7;
    final int ZERO_DAYS = 0;
    final int NOT_PRESENT = -1;

    //Constructor.
    public FrequencyWeekly(ArrayList<DayOfWeek> weekDays){
        this.weekDays = weekDays;
    }

    //Pre: ???
    //Post: ???
    private int daysToAddToDate(DayOfWeek day, DayOfWeek nextDay){
        int valueOfDay = day.getValue();
        int valueOfNextDay = nextDay.getValue();
        int daysToAdd = (valueOfNextDay - valueOfDay + NUMBER_OF_DAYS) % NUMBER_OF_DAYS;

        if (daysToAdd == ZERO_DAYS) daysToAdd = NUMBER_OF_DAYS;

        return daysToAdd;
    }

    //Pre: ???
    //Post: ???
    private DayOfWeek whatIsTheNextDay(int indexDay){
        final int lastIndexInArray = this.weekDays.size() - 1;

        return indexDay == lastIndexInArray ? this.weekDays.get(0) : this.weekDays.get(indexDay + 1);
    }

    //Pre: receives the DateTime.
    //Post: Given a date, returns the following event s date.
    @Override
    public LocalDateTime nextEventDateTime(LocalDateTime date){
        DayOfWeek day = date.getDayOfWeek();
        int dayIndexInArray = this.weekDays.indexOf(day);
        
        if (dayIndexInArray == NOT_PRESENT) return null;

        DayOfWeek nextDay = this.whatIsTheNextDay(dayIndexInArray); 
        int numberOfDays = this.daysToAddToDate(day, nextDay);
        LocalDate datePlus = date.plusDays(numberOfDays).toLocalDate();

        if (noNextEvent(datePlus)) return null;

        return date.plusDays(numberOfDays);
    }
}
