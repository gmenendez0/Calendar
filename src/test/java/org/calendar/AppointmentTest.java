package org.calendar;

import org.calendar.appointment.Appointment;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppointmentTest {

    //Post: Tests that setTitle sets the title correctly.
    @Test
    public void setTitle(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");
        appointment.setTitle("Hello2");

        var title = appointment.getTitle();

        assertEquals("Hello2", title);
    }

    //Post: Tests that setDescription sets the description correctly.
    @Test
    public void setDescription(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");
        appointment.setDescription("Hello from Paris");

        var description = appointment.getDescription();

        assertEquals("Hello from Paris", description);
    }

    //Post: Tests that setCompleted sets the completed attribute correctly.
    @Test
    public void setCompleted(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");

        appointment.setCompleted(true);
        var completed = appointment.isCompleted();

        assertTrue(completed);
    }

    //Post: Tests that destroy method sets the destroyed attribute on true.
    @Test
    public void destroy(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");

        appointment.destroy();
        var destroyed = appointment.isDestroyed();

        assertTrue(destroyed);
    }

    //Post: Tests the getTitle return value.
    @Test
    public void getTitle(){
        var appointment = new Appointment(1, "Hello", "");

        var titulo = appointment.getTitle();

        assertEquals("Hello", titulo);
    }

    //Post: Tests the getDescription return value.
    @Test
    public void getDescription(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");

        var description = appointment.getDescription();

        assertEquals("Hello from Buenos Aires", description);
    }

    //Post: Tests the isCompleted return value.
    @Test
    public void isCompleted(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");

        var completed = appointment.isCompleted();

        assertFalse(completed);
    }

    //Post: Tests the isDestroyed return value.
    @Test
    public void isDestroyed(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");

        var destroyed = appointment.isDestroyed();

        assertFalse(destroyed);
    }
}