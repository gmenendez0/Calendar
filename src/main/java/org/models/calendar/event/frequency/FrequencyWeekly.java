package org.models.calendar.event.frequency;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FrequencyWeekly extends Frequency {
    private final String subtype = "FrequencyWeekly";
    private List<DayOfWeek> weekDays;
    @JsonIgnore
    final int NUMBER_OF_DAYS = 7;
    @JsonIgnore
    final int ZERO_DAYS = 0;
    @JsonIgnore
    final int NOT_PRESENT = -1;
    @JsonIgnore
    final int FIRST_INDEX = 0;
    @JsonIgnore
    final int ONE_POSITION = 1;
    @JsonIgnore
    final int ARRAY_POSITION_DIFFERENCE = 1;

    public FrequencyWeekly(List<DayOfWeek> weekDays, LocalDate deadline){
        this.weekDays = weekDays;
        this.deadline = deadline;
    }

    //Empty constructor for persistence (Jackson).
    public FrequencyWeekly(){}

    //Post: getter needed for persistence.
    public List<DayOfWeek> getWeekDays(){
        return weekDays;
    }

    //Post: setter needed for persistence.
    public void setWeekDays(List<DayOfWeek> weekDays){
        this.weekDays = weekDays;
    }


    //Post: Returns the amount of days until next event.
    private int daysToAddToDate(DayOfWeek day, DayOfWeek nextDay){
        int valueOfDay = day.getValue();
        int valueOfNextDay = nextDay.getValue();
        int daysToAdd = (valueOfNextDay - valueOfDay + NUMBER_OF_DAYS) % NUMBER_OF_DAYS;

        if (daysToAdd == ZERO_DAYS) daysToAdd = NUMBER_OF_DAYS;

        return daysToAdd;
    }

    //Post: Returns the next day in the weekDays array.
    private DayOfWeek whatIsTheNextDay(int indexDay){
        final int lastIndexInArray = this.weekDays.size() - ARRAY_POSITION_DIFFERENCE;

        return indexDay == lastIndexInArray ? this.weekDays.get(FIRST_INDEX) : this.weekDays.get(indexDay + ONE_POSITION);
    }

    //@inheritDoc
    @Override
    public LocalDateTime nextRepetitionDateTime(LocalDateTime date){
        DayOfWeek day = date.getDayOfWeek();
        int dayIndexInArray = this.weekDays.indexOf(day);

        if (dayIndexInArray == NOT_PRESENT) return null;

        DayOfWeek nextDay = this.whatIsTheNextDay(dayIndexInArray);
        int numberOfDays = this.daysToAddToDate(day, nextDay);
        LocalDate datePlus = date.plusDays(numberOfDays).toLocalDate();

        if(noNextEvent(datePlus)) return null;
        if(date.toLocalDate().isEqual(datePlus)) return null;
        return date.plusDays(numberOfDays);
    }

    public String toString(){
        return "The frequency type is Weekly and your deadline is " + this.deadline.toString() + ".\n The days that repeat are: " + this.getWeekDays().toString();
    }

}