package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

public class Frecuency {

    private int interval;

    private ArrayList<DayOfWeek> weekDays;

    private char frecuency;

    private void sortArrayOfDay(){
        Collections.sort(this.weekDays);
    }

    public Frecuency(char frecuency){
        this.frecuency = frecuency;
        this.interval = 1;
    }

    public Frecuency(char frecuency, ArrayList<DayOfWeek> weekDays){
        this.frecuency = frecuency;
        this.weekDays = weekDays;
        this.sortArrayOfDay();
    }

    public Frecuency(char frecuency, int interval){
        this.frecuency = frecuency;
        this.interval = interval;
    }

    public LocalDateTime nextDate(LocalDateTime date){
        LocalDateTime next = date;
        switch (this.frecuency){
            case 'D':
                next = date.plus(this.interval, ChronoUnit.DAYS);
                break;
            case 'M':
                next = date.plus(this.interval, ChronoUnit.MONTHS);
                break;
            case 'Y':
                next = date.plus(this.interval, ChronoUnit.YEARS);
                break;
            case 'W':
                DayOfWeek day = date.getDayOfWeek();
                DayOfWeek nextDay;
                int indexDay = this.weekDays.indexOf(day);
                if (indexDay == weekDays.size()-1){
                    nextDay = this.weekDays.get(0);
                } else {
                    nextDay = this.weekDays.get(indexDay+1);
                }
                int numberOfDays = (nextDay.getValue() - day.getValue() + 7) % 7;
                next = date.plusDays(numberOfDays);
                break;
        }
        return next;
    }

    public void changeFrecuency(char frecuency){
        this.frecuency = frecuency;
        this.interval = 1;
    }

    public void changeFrecuency(char frecuency, int interval){
        this.frecuency = frecuency;
        this.interval = interval;
    }

    public void changeFrecuency(char frecuency, ArrayList<DayOfWeek> weekDays){
        this.frecuency = frecuency;
        this.weekDays = weekDays;
    }

}
