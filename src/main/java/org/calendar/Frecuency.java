package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Frecuency {
    public abstract LocalDateTime nextDate(LocalDateTime date);
}