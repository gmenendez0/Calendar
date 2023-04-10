package org.calendar;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppointmentTest {

    @Test
    public void setTitle(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");
        appointment.setTitle("Hello2");

        var title = appointment.getTitle();

        assertEquals("Hello2", title);
    }

    @Test
    public void setDescription(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");
        appointment.setDescription("Hello from Paris");

        var description = appointment.getDescription();

        assertEquals("Hello from Paris", description);
    }

    @Test
    public void setCompleted(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");

        appointment.setCompleted(true);
        var completed = appointment.isCompleted();

        assertTrue(completed);
    }

    @Test
    public void destroy(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");

        appointment.destroy();
        var destroyed = appointment.isDestroyed();

        assertTrue(destroyed);
    }

    @Test
    public void getTitle(){
        var appointment = new Appointment(1, "Hello", "");

        var titulo = appointment.getTitle();

        assertEquals("Hello", titulo);
    }

    @Test
    public void getDescription(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");

        var description = appointment.getDescription();

        assertEquals("Hello from Buenos Aires", description);
    }

    @Test
    public void isCompleted(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");

        var completed = appointment.isCompleted();

        assertFalse(completed);
    }

    @Test
    public void isDestroyed(){
        var appointment = new Appointment(1, "Hello", "Hello from Buenos Aires");

        var destroyed = appointment.isDestroyed();

        assertFalse(destroyed);
    }
}