package org;

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

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        var calendar = new Calendar();

        Event evento1 = new PeriodTimeEvent("EVENTO", "Prueba de Json", LocalDateTime.of(2023,5,18,20,30,10), LocalDateTime.of(2023,5,18,20,30,10).plusDays(10));
        Frequency frecuencia1 = new FrequencyMonthly(LocalDate.of(2025, 5,28));
        evento1.setFrequency(frecuencia1);

        Task tarea1 = new WholeDayTask("TAREAAA", "Descripcion de la tarea", LocalDate.of(2023, 5, 20));
        tarea1.addAlarm(new NotificationAlarm(0, LocalDateTime.of(10,10,10,10,10,10)));
        tarea1.addAlarm(new EmailAlarm(1, LocalDateTime.of(10,10,10,10,15,10)));
        tarea1.addAlarm(new SoundAlarm(2, LocalDateTime.of(10,10,10,10,20,10)));

        calendar.addAppointment(tarea1);
        calendar.addAppointment(evento1);

        var jsonAppointment = calendar.getAppointmentsBetween(LocalDateTime.of(2023,5,1,0,0,0), LocalDateTime.of(2023,6,1,0,0,0));

        System.out.println(jsonAppointment.size());
    }
}