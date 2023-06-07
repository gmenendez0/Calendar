package org.models.calendar.task;

import org.models.calendar.appointment.Appointment;
import org.models.calendar.visitor.AppointmentsVisitor;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class WholeDayTaskTest {
    private WholeDayTask wholeDayTask;
    private Appointment appointment;

    @Before
    public void initialize(){
        wholeDayTask = new WholeDayTask("title", "description", LocalDate.of(2020, 1, 1));
        appointment = new WholeDayTask("Hello", "Hello from Buenos Aires", LocalDate.of(2020, 1, 2));
    }

    //Post: Tests the return value of getStartDate.
    @Test
    public void getStartDate() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);

        var taskStartDate = wholeDayTask.getStartDateTime().toLocalDate();

        assertEquals(startDate, taskStartDate);
    }

    //Post: Tests the correct behaviour of setStartDate.
    @Test
    public void setStartDate() {
        LocalDate newStartDate = LocalDate.of(2020, 1, 2);

        wholeDayTask.setStartDate(newStartDate);
        LocalDate startDate = wholeDayTask.getStartDateTime().toLocalDate();

        assertEquals(newStartDate, startDate);
    }

    //Post: Tests that acceptVisitor method behaves correctly.
    @Test
    public void acceptVisitor(){
        var visitor = new AppointmentsVisitor();
        var firstDateTime = LocalDateTime.of(2019,1,15,0,0,0);
        var secondDateTime = LocalDateTime.of(2021,1,31,12,0,0);

        List<Appointment> selectedAppointments = wholeDayTask.acceptVisitor(visitor, firstDateTime, secondDateTime);

        assertEquals(1, selectedAppointments.size());
    }

    //***APPOINTMENT TESTS***

    //Post: Tests the getTitle return value.
    @Test
    public void getTitle(){
        var titulo = appointment.getTitle();

        assertEquals("Hello", titulo);
    }

    //Post: Tests the getDescription return value.
    @Test
    public void getDescription(){
        var description = appointment.getDescription();

        assertEquals("Hello from Buenos Aires", description);
    }

    //Post: Tests the isCompleted return value.
    @Test
    public void isCompleted(){
        var completed = appointment.getCompleted();

        assertFalse(completed);
    }

    //Post: Tests that destroy method sets the destroyed attribute on true.
    @Test
    public void destroy(){
        appointment.setDestroyed();
        var destroyed = appointment.getDestroyed();

        assertTrue(destroyed);
    }

    //Post: Tests the isDestroyed return value.
    @Test
    public void isDestroyed(){
        var destroyed = appointment.getDestroyed();

        assertFalse(destroyed);
    }

    //Post: Tests that setTitle sets the title correctly.
    @Test
    public void setTitle(){
        appointment.setTitle("Hello2");

        var title = appointment.getTitle();

        assertEquals("Hello2", title);
    }

    //Post: Tests that setDescription sets the description correctly.
    @Test
    public void setDescription(){
        appointment.setDescription("Hello from Paris");

        var description = appointment.getDescription();

        assertEquals("Hello from Paris", description);
    }

    //Post: Tests that setCompleted sets the completed attribute correctly.
    @Test
    public void setCompleted(){
        appointment.setCompleted(true);
        var completed = appointment.getCompleted();

        assertTrue(completed);
    }
}