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
    private final String pathJSON = "src/main/resources/appointment.json";
    private Calendar calendar;
    private FileHandler fileHandler;

    private List<Appointment> jsonAppointment;

    @Before
    public void initialize(){

        calendar = new Calendar();

        Event evento1 = new PeriodTimeEvent("EVENTO", "Prueba de Json", LocalDateTime.of(2023,5,18,20,30,10),
                LocalDateTime.of(2023,5,18,20,30,10).plusDays(10));
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

    private void getAppointmentList(){
        jsonAppointment = calendar.getAppointmentsBetween(LocalDateTime.of(2023,5,1,0,0,0), LocalDateTime.of(2023,6,1,0,0,0));
    }

    @Test
    public void testA_saveTest() throws IOException {
        calendar.saveAppointments(fileHandler, pathJSON);
        File file = new File(pathJSON);
        assertTrue(file.exists());
        assertTrue(file.isFile());
    }

    private void amountInTheFile(int amount) throws IOException{
        calendar.recoverAppointments(fileHandler, pathJSON);
        getAppointmentList();
        assertEquals(amount, jsonAppointment.size());
    }

    @Test
    public void testB_sameAmountSavedAndRetrieved() throws IOException{
        amountInTheFile(2);
    }

    @Test
    public void testC_checkTypes() throws IOException{
        calendar.recoverAppointments(fileHandler, pathJSON);
        assertEquals("Task", jsonAppointment.get(0).getType());
        assertEquals("Event", jsonAppointment.get(1).getType());
    }

    @Test
    public void testD_addNewAppointment() throws IOException{
        Event evento2 = new WholeDayEvent("Anual", "Prueba con evento anual", LocalDate.of(2023,5,10));
        Frequency frecuencia2 = new FrequencyAnnual();
        evento2.setFrequency(frecuencia2);

        Event evento3 = new WholeDayEvent("SIN", "FRECUENCIA", LocalDate.of(2023,5,25));

        calendar.addAppointment(evento2);
        calendar.addAppointment(evento3);

        calendar.saveAppointments(fileHandler, pathJSON);
        File file = new File(pathJSON);
        assertTrue(file.exists());
    }

    @Test
    public void testE_newSameAmountSavedAndRetrieved() throws IOException{
        amountInTheFile(4);
    }

    @Test
    public void testF_verifyTitleOfTheNewAppointment() throws IOException{
        calendar.recoverAppointments(fileHandler, pathJSON);

        assertEquals("Anual", jsonAppointment.get(0).getTitle());
        assertEquals("EVENTO", jsonAppointment.get(1).getTitle());
        assertEquals("TAREAAA", jsonAppointment.get(2).getTitle());
        assertEquals("SIN", jsonAppointment.get(3).getTitle());
    }

    @Test
    public void testG_verifyDescriptionOfTheNewAppointment() throws IOException{
        calendar.recoverAppointments(fileHandler, pathJSON);

        assertEquals("Prueba con evento anual", jsonAppointment.get(0).getDescription());
        assertEquals("Prueba de Json", jsonAppointment.get(1).getDescription());
        assertEquals("Descripcion de la tarea", jsonAppointment.get(2).getDescription());
        assertEquals("FRECUENCIA", jsonAppointment.get(3).getDescription());

    }

    @Test
    public void testH_verifySubtypesOfTheNewAppointment() throws IOException{
        calendar.recoverAppointments(fileHandler, pathJSON);

        assertEquals("Event", jsonAppointment.get(0).getType());
        assertEquals("Event", jsonAppointment.get(1).getType());
        assertEquals("Task", jsonAppointment.get(2).getType());
        assertEquals("Event", jsonAppointment.get(4).getType());

    }
}