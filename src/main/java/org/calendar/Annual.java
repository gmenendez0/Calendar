package org.calendar;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Annual implements Frecuency{
    @Override
    public LocalDateTime nextDate(LocalDateTime date) {
        return date.plus(1, ChronoUnit.YEARS);
    }
}
