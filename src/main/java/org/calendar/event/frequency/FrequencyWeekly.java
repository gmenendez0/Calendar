package org.calendar.event.frequency;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FrequencyWeekly extends Frequency {
    private final ArrayList<DayOfWeek> weekDays;
    final int NUM_DAY = 7;  //! Esto que significa? Estaría más claro si la constante tuviera un nombre más descriptivo.

    //Constructor.
    public FrequencyWeekly(ArrayList<DayOfWeek> weekDays){
        this.weekDays = weekDays;
    }

    //! ESTO HAY QUE REFACTORIZARLO PORQUE NO SE ENTIENDE. ADEMAS, HAY QUE CORREGIR LITERALES.
    //Pre: receives the DateTime.
    //Post: Given a date, returns the following event s date.
    @Override
    public LocalDateTime nextEventDateTime(LocalDateTime date){
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


        if (noNextEvent(datePlus)) return null;

        return date.plusDays(numberOfDays);
    }
}
