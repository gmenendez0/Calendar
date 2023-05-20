package calendar_org.calendar.event.frequency;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrequencyMonthly extends Frequency {
    private String subtype = "FrequencyMonthly";
    @JsonIgnore
    final int ONE_MONTH = 1;
    @JsonIgnore
    private int monthsToAdd;

    public FrequencyMonthly(LocalDate deadline){
        this.monthsToAdd = ONE_MONTH;
        this.deadline = deadline;
    }

    public FrequencyMonthly(){}

    public String getSubtype(){
        return subtype;
    }

    public void setSubtype(String subtype){
        this.subtype = subtype;
    }

    //@inheritDoc
    @Override
    public LocalDateTime nextRepetitionDateTime(LocalDateTime date){
        LocalDateTime dayTime = date.plus(this.monthsToAdd, ChronoUnit.MONTHS);
        LocalDate day = dayTime.toLocalDate();

        if (super.noNextEvent(day)) return null;

        return dayTime;
    }

}
