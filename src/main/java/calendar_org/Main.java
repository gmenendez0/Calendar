package calendar_org;

import calendar_org.calendar.Calendar;
import calendar_org.calendar.alarms.EmailAlarm;
import calendar_org.calendar.alarms.NotificationAlarm;
import calendar_org.calendar.alarms.SoundAlarm;
import calendar_org.calendar.appointment.Appointment;
import calendar_org.calendar.event.Event;
import calendar_org.calendar.event.frequency.Frequency;
import calendar_org.calendar.event.frequency.FrequencyAnnual;
import calendar_org.calendar.event.frequency.FrequencyDaily;
import calendar_org.calendar.event.PeriodTimeEvent;
import calendar_org.calendar.event.WholeDayEvent;
import calendar_org.calendar.event.frequency.FrequencyMonthly;
import calendar_org.calendar.task.Task;
import calendar_org.calendar.task.WholeDayTask;
import calendar_org.file_handler.FileHandler;
import calendar_org.file_handler.JsonFileHandlerStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

        c.saveAppointment(g, "calendar.json");

        List<Appointment> array = c.readAppointmentFile(g, "calendar.json");

        System.out.println(array);

        for (Appointment apo : array) {
            System.out.println(apo.getId());
            System.out.println(apo.getTitle());
            System.out.println(apo.getDescription());
        }

    }
}