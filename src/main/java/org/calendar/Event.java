package org.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event extends Appointment{

    private boolean isRepeated;
    private Duration duration;
    private char frecuency;
    private Daily frecuencyDay;
    private Weekly frecuencyWeek;
    private Monthly frecuencyMonth;
    private Annual frecuencyYear;

    //El event tiene dos constructores debido a que la clase Duration puede tiene dos constructores.
    //Basicamente si queremos crear un evento que dure un dia entero, simplemente pasandole la  fecha es suficiente.
    //y si queremos fecha y hora especifica, se envia por argumento y listo.

    //Calculo que con el UI nos enviara un 'mensaje' a la clase con el tipo de frecuencia que tendra
    //este mensaje sera: D (daily), W(weekly), M(Monthly) y A(annual), son letras que seran string.

    public Event(int id, String title, String description, boolean isRepeated,
                 LocalDateTime startEvent, LocalDateTime endingEvent, char frecuency){
        super(id, title, description);
        this.isRepeated = isRepeated;
        this.duration = new Duration(startEvent, endingEvent);
        this.frecuency = frecuency;
        switch(frecuency){
            case 'M':
                this.frecuencyMonth = new Monthly();
                break;
            case 'Y':
                this.frecuencyYear = new Annual();
                break;    
        }
    }

    public Event(int id, String title, String description, boolean isRepeated,
                 LocalDate eventDate, char frecuency){
        super(id, title, description);
        this.isRepeated = isRepeated;
        this.duration = new Duration(eventDate);
        this.frecuency = frecuency;
        switch(frecuency){
            case 'M':
                this.frecuencyMonth = new Monthly();
                break;
            case 'Y':
                this.frecuencyYear = new Annual();
                break;
        }
    }

    public Event(int id, String title, String description, boolean isRepeated,
                 LocalDateTime startEvent, LocalDateTime endingEvent, char frecuency,
                 ArrayList<DayOfWeek> weekDay){
        super(id, title, description);
        this.isRepeated = isRepeated;
        this.duration = new Duration(startEvent, endingEvent);
        this.frecuency = frecuency;
        this.frecuencyWeek = new Weekly(weekDay);
    }

    public Event(int id, String title, String description, boolean isRepeated,
                 LocalDate eventDate, char frecuency, ArrayList<DayOfWeek> weekDay){
        super(id, title, description);
        this.isRepeated = isRepeated;
        this.duration = new Duration(eventDate);
        this.frecuency = frecuency;
        this.frecuencyWeek = new Weekly(weekDay);
    }

    public Event(int id, String title, String description, boolean isRepeated,
                 LocalDateTime startEvent, LocalDateTime endingEvent, char frecuency,
                 int interval){
        super(id, title, description);
        this.isRepeated = isRepeated;
        this.duration = new Duration(startEvent, endingEvent);
        this.frecuency = frecuency;
        this.frecuencyDay = new Daily(interval);
    }

    public Event(int id, String title, String description, boolean isRepeated,
                 LocalDate eventDate, char frecuency, int interval){
        super(id, title, description);
        this.isRepeated = isRepeated;
        this.duration = new Duration(eventDate);
        this.frecuency = frecuency;
        this.frecuencyDay = new Daily(interval);
    }
    
    public LocalDateTime nextDateEvent(LocalDateTime date){
        LocalDateTime next = date;
        switch (this.frecuency){
            case 'D':
                next = this.frecuencyDay.nextDate(date);
                break;
            case 'W':
                next = this.frecuencyWeek.nextDate(date);
                break;
            case 'M':
                next = this.frecuencyMonth.nextDate(date);
                break;
            case 'Y':
                next = this.frecuencyYear.nextDate(date);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.frecuency);
        }
        return next;
    }
    
}
