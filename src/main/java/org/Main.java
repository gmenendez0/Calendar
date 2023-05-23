package org;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.calendar.Calendar;
import org.calendar.alarms.EmailAlarm;
import org.calendar.alarms.NotificationAlarm;
import org.calendar.alarms.SoundAlarm;
import org.calendar.event.Event;
import org.calendar.event.PeriodTimeEvent;
import org.calendar.event.frequency.Frequency;
import org.calendar.event.frequency.FrequencyMonthly;
import org.calendar.task.Task;
import org.calendar.task.WholeDayTask;
import org.file_handler.FileHandler;
import org.file_handler.JsonFileHandlerStrategy;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IOException {

    }
}