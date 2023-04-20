package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class FrecuencyWeekly extends Frecuency{
    private ArrayList<DayOfWeek> weekDays;
    final int NUM_DAY = 7;

    //Constructor.
    public FrecuencyWeekly(ArrayList<DayOfWeek> weekDays){
        this.weekDays = weekDays;
    }

    //Pre: receives the LocalDateTime.
    //Post: returns the next event date.
    @Override
    public LocalDateTime nextDate(LocalDateTime date){
        DayOfWeek day = date.getDayOfWeek();
        DayOfWeek nextDay;
        int indexDay = this.weekDays.indexOf(day);
        if (indexDay == weekDays.size()-1){
            nextDay = this.weekDays.get(0);
        } else {
            nextDay = this.weekDays.get(indexDay+1);
        }
        int numberOfDays = (nextDay.getValue() - day.getValue() + NUM_DAY) % NUM_DAY;
        if (numberOfDays == 0){
            numberOfDays = NUM_DAY;
        }
        LocalDate datePlus = date.plusDays(numberOfDays).toLocalDate();
        if (super.hasADeadline()){
            if (super.hasExceededDeadline(datePlus)){
                return null;
            }
        }
        return date.plusDays(numberOfDays);
    }

}
