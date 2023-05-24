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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileHandlerTest {
    private final String pathJSON = "src/main/resources/appointment.json";
    private Calendar calendar;
    private FileHandler fileHandler;
    private final  LocalDateTime initMonth = LocalDateTime.of(2023,5,1,00,00,00);
    private final LocalDateTime finalMonth = LocalDateTime.of(2023,6,1,00,00,00);
    private final int INDEX_TASK = 0;
    private final int INDEX_PERIOD_TIME_EVENT = 1;
    private final int INDEX_WHOLE_DAY_EVENT_1 = 2;
    private final int INDEX_WHOLE_DAY_EVENT_2 = 3;

    private List<Appointment> jsonAppointment;

    //An object of class 'Calendar' is initialized with two appointment.
    @Before
    public void initialize(){

        calendar = new Calendar();

        Event evento1 = new PeriodTimeEvent("EVENTO", "Prueba de Json", LocalDateTime.of(2023,05,18,20,30,10),
                LocalDateTime.of(2023,5,28,20,30,10));
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

    //Test that verifies if the 'appointment.json' file is created correctly.
    @Test
    public void testA_saveTest() throws IOException {
        calendar.saveAppointments(fileHandler, pathJSON);
        File file = new File(pathJSON);
        assertTrue(file.exists());
        assertTrue(file.isFile());
    }

    private void recoverListAppointment() throws IOException{
        calendar.recoverAppointments(fileHandler, pathJSON);
        jsonAppointment = calendar.getAppointmentsBetween(initMonth, finalMonth);

    }

    //Test that verifies that the number of objects loaded after reading the file is correct.
    @Test
    public void testB_sameAmountSavedAndRetrieved() throws IOException {
        recoverListAppointment();
        assertEquals(2, jsonAppointment.size());
    }

    //Test that verifies if the types of the objects are correct.
    @Test
    public void testC_checkTypes() throws IOException {
        recoverListAppointment();
        assertEquals("Task", jsonAppointment.get(INDEX_TASK).getType());
        assertEquals("Event", jsonAppointment.get(INDEX_PERIOD_TIME_EVENT).getType());
    }

    //Test that verifies if new appointments are loaded correctly in the calendar and if it is saved correctly in the file.
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

    //Test that verifies that the number of objects loaded after reading the file is correct.
    @Test
    public void testE_newSameAmountSavedAndRetrieved() throws IOException{
        recoverListAppointment();
        assertEquals(4, jsonAppointment.size());
    }

    //Test that verifies if the title of the objects are correct.
    @Test
    public void testF_verifyTitleOfTheNewAppointment() throws IOException{
        recoverListAppointment();

        assertEquals("TAREAAA", jsonAppointment.get(INDEX_TASK).getTitle());
        assertEquals("EVENTO", jsonAppointment.get(INDEX_PERIOD_TIME_EVENT).getTitle());
        assertEquals("Anual", jsonAppointment.get(INDEX_WHOLE_DAY_EVENT_1).getTitle());
        assertEquals("SIN", jsonAppointment.get(INDEX_WHOLE_DAY_EVENT_2).getTitle());
    }

    //Test that verifies if the description of the objects are correct.
    @Test
    public void testG_verifyDescriptionOfTheNewAppointment() throws IOException{
        recoverListAppointment();

        assertEquals("Descripcion de la tarea", jsonAppointment.get(INDEX_TASK).getDescription());
        assertEquals("Prueba de Json", jsonAppointment.get(INDEX_PERIOD_TIME_EVENT).getDescription());
        assertEquals("Prueba con evento anual", jsonAppointment.get(INDEX_WHOLE_DAY_EVENT_1).getDescription());
        assertEquals("FRECUENCIA", jsonAppointment.get(INDEX_WHOLE_DAY_EVENT_2).getDescription());

    }

    //Test that verifies if the subtypes of the objects are correct.
    @Test
    public void testH_verifySubtypesOfTheNewAppointment() throws IOException{
        recoverListAppointment();

        assertEquals("Task", jsonAppointment.get(INDEX_TASK).getType());
        assertEquals("Event", jsonAppointment.get(INDEX_PERIOD_TIME_EVENT).getType());
        assertEquals("Event", jsonAppointment.get(INDEX_WHOLE_DAY_EVENT_1).getType());
        assertEquals("Event", jsonAppointment.get(INDEX_WHOLE_DAY_EVENT_2).getType());

    }
}