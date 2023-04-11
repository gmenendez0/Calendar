package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Weekly implements Frecuency{
    private ArrayList<DayOfWeek> weekDays;

    public Weekly(ArrayList<DayOfWeek> weekDays){
        this.weekDays = weekDays;
    }

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
        int numberOfDays = (nextDay.getValue() - day.getValue() + 7) % 7;
        return date.plusDays(numberOfDays);
    }

    public void setWeekDays(ArrayList<DayOfWeek> weekDays) {
        this.weekDays = weekDays;
    }

}
