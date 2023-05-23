package org.file_handler;

import org.calendar.Calendar;
import org.calendar.alarms.EmailAlarm;
import org.calendar.alarms.NotificationAlarm;
import org.calendar.alarms.SoundAlarm;
import org.calendar.appointment.Appointment;
import org.calendar.event.Event;
import org.calendar.event.PeriodTimeEvent;
import org.calendar.event.WholeDayEvent;
import org.calendar.event.frequency.Frequency;
import org.calendar.event.frequency.FrequencyAnnual;
import org.calendar.event.frequency.FrequencyMonthly;
import org.calendar.task.Task;
import org.calendar.task.WholeDayTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileHandlerTest {
    private final String pathJSON = "calendar.json";
    private Calendar calendar;
    private FileHandler fileHandler;

    @Before
    public void initialize(){

        calendar = new Calendar();

        Event evento1 = new PeriodTimeEvent("EVENTO", "Prueba de Json", LocalDateTime.of(2023,05,18,20,30,10),
                LocalDateTime.of(2023,05,18,20,30,10).plusDays(10));
        Frequency frecuencia1 = new FrequencyMonthly(LocalDate.of(2025, 5,28));
        evento1.setFrequency(frecuencia1);

        Task tarea1 = new WholeDayTask("TAREAAA", "Descripcion de la tarea", LocalDate.of(2023, 5, 20));
        tarea1.addAlarm(new NotificationAlarm(0, LocalDateTime.of(10,10,10,10,10,10)));
        tarea1.addAlarm(new EmailAlarm(1, LocalDateTime.of(10,10,10,10,15,10)));
        tarea1.addAlarm(new SoundAlarm(2, LocalDateTime.of(10,10,10,10,20,10)));

        calendar.addAppointment(tarea1);
        calendar.addAppointment(evento1);

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        JsonFileHandlerStrategy j = new JsonFileHandlerStrategy(objectMapper);
        fileHandler = new FileHandler(j);
    }

    @Test
    public void saveTest(){
        calendar.saveAppointment(fileHandler, pathJSON);
        File file = new File(pathJSON);
        assertTrue(file.exists());
        assertTrue(file.isFile());
    }

    private void amountInTheFile(int amount){
        List<Appointment> jsonAppointment = calendar.readAppointmentFile(fileHandler, pathJSON);
        assertEquals(amount, jsonAppointment.size());
    }

    @Test
    public void sameAmountSavedAndRetrieved(){
        amountInTheFile(2);
    }

    @Test
    public void checkTypes(){
        List<Appointment> jsonAppointment = calendar.readAppointmentFile(fileHandler, pathJSON);
        assertEquals("Task", jsonAppointment.get(0).getType());
        assertEquals("Event", jsonAppointment.get(1).getType());
    }

    @Test
    public void addNewAppointment(){
        Event evento2 = new WholeDayEvent("Navidad", "Prueba con evento anual", LocalDate.of(2023,12,25));
        Frequency frecuencia2 = new FrequencyAnnual();
        evento2.setFrequency(frecuencia2);

        Event evento3 = new WholeDayEvent("Diario", "Es un evento diario", LocalDate.of(2023,6,25));
        Frequency frecuencia3 = new FrequencyDaily(1,LocalDate.of(2023,6,30));
        evento3.setFrequency(frecuencia3);

        Event evento4 = new WholeDayEvent("SIN", "FRECUENCIA", LocalDate.of(2023,5,18));

        calendar.addAppointment(evento2);
        calendar.addAppointment(evento3);
        calendar.addAppointment(evento4);

        calendar.saveAppointment(fileHandler, pathJSON);
        File file = new File(pathJSON);
        assertTrue(file.exists());
    }

    @Test
    public void newSameAmountSavedAndRetrieved(){
        amountInTheFile(5);
    }

    @Test
    public void verifyTitleOfTheNewAppointment(){
        List<Appointment> newJsonAppointment = calendar.readAppointmentFile(fileHandler, pathJSON);

        assertEquals("TAREAAA", newJsonAppointment.get(0).getTitle());
        assertEquals("EVENTO", newJsonAppointment.get(1).getTitle());
        assertEquals("Navidad", newJsonAppointment.get(2).getTitle());
        assertEquals("Diario", newJsonAppointment.get(3).getTitle());
        assertEquals("SIN", newJsonAppointment.get(4).getTitle());

    }

    @Test
    public void verifyDescriptionOfTheNewAppointment(){
        List<Appointment> newJsonAppointment = calendar.readAppointmentFile(fileHandler, pathJSON);

        assertEquals("Descripcion de la tarea", newJsonAppointment.get(0).getDescription());
        assertEquals("Prueba de Json", newJsonAppointment.get(1).getDescription());
        assertEquals("Prueba con evento anual", newJsonAppointment.get(2).getDescription());
        assertEquals("Es un evento diario", newJsonAppointment.get(3).getDescription());
        assertEquals("FRECUENCIA", newJsonAppointment.get(4).getDescription());

    }

    @Test
    public void verifySubtypesOfTheNewAppointment(){
        List<Appointment> newJsonAppointment = calendar.readAppointmentFile(fileHandler, pathJSON);

        assertEquals("Task", newJsonAppointment.get(0).getType());
        assertEquals("Event", newJsonAppointment.get(1).getType());
        assertEquals("Event", newJsonAppointment.get(2).getType());
        assertEquals("Event", newJsonAppointment.get(3).getType());
        assertEquals("Event", newJsonAppointment.get(4).getType());

    }
}