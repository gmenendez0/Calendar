package org.calendar.alarms;

public abstract class AlarmDecorator implements Alarm {
    public Alarm alarm;

    public AlarmDecorator(Alarm alarm){
        //? Aquí, al constructor del decorador le llegara uno de 3 tipos de alarma: Notif, sonido o email.
        this.alarm = alarm;
    }

    @Override
    public void ring() {
        alarm.ring();
    }
}
