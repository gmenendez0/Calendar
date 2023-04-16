package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class FrecuencyWeekly extends Frecuency{
    private ArrayList<DayOfWeek> weekDays;

    public FrecuencyWeekly(ArrayList<DayOfWeek> weekDays){
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
        if (numberOfDays == 0){
            numberOfDays = 7;
        }
        LocalDate datePlus = date.plusDays(numberOfDays).toLocalDate();
        if (super.getDeadline() != null){
            LocalDate lastDate = super.getDeadline();
            if (datePlus.compareTo(lastDate) >= 0){
                return null;
            }
        }
        return date.plusDays(numberOfDays);
    }

}
