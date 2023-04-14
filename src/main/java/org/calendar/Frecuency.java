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

    //Pre: receives the type of frequency (M or Y).
    //Constructor: monthly and yearly.
    public Frecuency(char frecuency){
        this.frecuency = frecuency;
        this.interval = 1;
    }

    //Pre: receives the type of frequency (W).
    //Constructor: weekly.
    public Frecuency(char frecuency, ArrayList<DayOfWeek> weekDays){
        this.frecuency = frecuency;
        this.weekDays = weekDays;
    }

    //Pre: receives the type of frequency (D).
    //Constructor: daily.
    public Frecuency(char frecuency, int interval){
        this.frecuency = frecuency;
        this.interval = interval;
    }

    //Pre: get a date.
    //Post: returns the next date according to the frequency.
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
                if (this.weekDays == null){
                    next = date.plus(this.interval, ChronoUnit.WEEKS);
                    break;
                }
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
            default:
                throw new IllegalStateException("Unexpected value: " + this.frecuency);
        }
        return next;
    }

    //Pre: receives the type of frequency (M or Y).
    public void changeFrecuency(char frecuency){
        this.frecuency = frecuency;
        this.interval = 1;
    }

    //Pre: receives the type of frequency (D) and interval of days.
    public void changeFrecuency(char frecuency, int interval){
        this.frecuency = frecuency;
        this.interval = interval;
    }

    //Pre: receives the type of frequency (W) and array of days of the week.
    public void changeFrecuency(char frecuency, ArrayList<DayOfWeek> weekDays){
        this.frecuency = frecuency;
        this.weekDays = weekDays;
    }

    public char getFrecuency(){
        return this.frecuency;
    }

}