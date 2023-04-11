package org.calendar;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Daily implements Frecuency{
    private int interval;

    public Daily(int interval){
        this.interval = interval;
    }

    @Override
    public LocalDateTime nextDate(LocalDateTime date){
        return date.plus(this.interval, ChronoUnit.DAYS);
    }

    public void  setInterval(int interval){
        this.interval = interval;
    }
}
