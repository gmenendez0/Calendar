package org;

import org.calendar.Calendar;
import org.calendar.alarms.EmailAlarm;
import org.calendar.alarms.NotificationAlarm;
import org.calendar.alarms.SoundAlarm;
import org.calendar.event.Event;
import org.calendar.event.PeriodTimeEvent;
import org.calendar.event.WholeDayEvent;
import org.calendar.event.frequency.Frequency;
import org.calendar.event.frequency.FrequencyAnnual;
import org.calendar.event.frequency.FrequencyDaily;
import org.calendar.event.frequency.FrequencyMonthly;
import org.calendar.task.Task;
import org.calendar.task.WholeDayTask;
import org.file_handler.FileHandler;
import org.file_handler.JsonFileHandlerStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Calendar c = new Calendar();

        Event evento1 = new PeriodTimeEvent("EVENTAZO", "Prueba de Json", LocalDateTime.of(2023,05,18,20,30,10),
                LocalDateTime.of(2023,05,18,20,30,10).plusDays(10));
        Frequency frecuencia1 = new FrequencyMonthly(LocalDate.of(2025, 5,28));
        evento1.setFrequency(frecuencia1);

        Event evento2 = new WholeDayEvent("Navidad", "Prueba de Json 2", LocalDate.of(2023,12,25));
        Frequency frecuencia2 = new FrequencyAnnual();
        evento2.setFrequency(frecuencia2);

        Event evento3 = new WholeDayEvent("Diario", "AAAAAAAAAAA", LocalDate.of(2023,6,25));
        Frequency frecuencia3 = new FrequencyDaily(1,LocalDate.of(2023,6,30));
        evento3.setFrequency(frecuencia3);

        Event evento4 = new WholeDayEvent("SIN", "FRECUENCIA", LocalDate.of(2023,5,18));

        Task tarea1 = new WholeDayTask("TAREAAA", "Descripcion de la tarea", LocalDate.now());
        tarea1.addAlarm(new NotificationAlarm(0, LocalDateTime.of(10,10,10,10,10,10)));
        tarea1.addAlarm(new EmailAlarm(1, LocalDateTime.of(10,10,10,10,15,10)));
        tarea1.addAlarm(new SoundAlarm(2, LocalDateTime.of(10,10,10,10,20,10)));

        c.addAppointment(evento2);
        c.addAppointment(tarea1);
        c.addAppointment(evento3);
        c.addAppointment(evento1);
        c.addAppointment(evento4);

        JsonFileHandlerStrategy j = new JsonFileHandlerStrategy();
        FileHandler g = new FileHandler(j);

        c.saveAppointment(g);

    }
}