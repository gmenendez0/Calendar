package org.models.calendar.visitor.appointment_details;

import org.junit.Test;
import org.models.calendar.event.Event;
import org.models.calendar.event.WholeDayEvent;
import org.models.calendar.event.frequency.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DetailsVisitorFrequencyEventTest {
    private final Event event = new WholeDayEvent();

    private final AppointmentDetailsVisitor visitor = new AppointmentDetailsVisitorImpl();

    @Test
    public void frequencyDiaryDetails(){
        String expected = "The frequency type is Daily and your deadline is 2023-06-15.\n Repeats every 10 days.";
        Frequency diary = new FrequencyDaily(10, LocalDate.of(2023, 6, 15));
        event.setFrequency(diary);
        String response = event.acceptVisitorDetailsFrequency(visitor);
        assertEquals(expected,response);
    }

    @Test
    public void frequencyWeeklyDetails(){
        String expected = "The frequency type is Weekly and your deadline is 2023-06-28.\n The days that repeat are: [MONDAY, TUESDAY, WEDNESDAY]";
        List<DayOfWeek> dayOfWeekList = List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY);
        Frequency weekly = new FrequencyWeekly(dayOfWeekList, LocalDate.of(2023, 6, 28));
        event.setFrequency(weekly);
        String response = event.acceptVisitorDetailsFrequency(visitor);
        assertEquals(expected,response);
    }

    @Test
    public void frequencyMonthlyDetails(){
        String expected = "The frequency type is Monthly and your deadline is 2023-06-16";
        Frequency monthly = new FrequencyMonthly(LocalDate.of(2023, 6, 16));
        event.setFrequency(monthly);
        String response = event.acceptVisitorDetailsFrequency(visitor);
        assertEquals(expected,response);
    }

    @Test
    public void frequencyAnnualDetails(){
        String expected = "The frequency type is Annual and your deadline is 2023-06-16";
        Frequency annual = new FrequencyAnnual(LocalDate.of(2023, 6, 16));
        event.setFrequency(annual);
        String response = event.acceptVisitorDetailsFrequency(visitor);
        assertEquals(expected,response);
    }

}