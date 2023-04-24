package org.calendar;

import org.calendar.alarms.*;
import org.calendar.task.WholeDayTask;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
    /*

        //El usuario viene y dice que quiere agregarle a X appointment una alarma de notification.
        Alarm alarmDeNotif = new NotificationAlarm(1, LocalDateTime.now());

        //Luego dice que quiere que la misma alarma le envie un email, además de la notification anterior.
        Alarm alarmaDeEmailDecorator = new EmailAlarmDecorator(2, LocalDateTime.now(), alarmDeNotif);
        //alarmaDeEmailDecorator.ring();

        //Por último dice que quiere que la misma alarma le emita un sonido, además de la notification y el email anterior.
        Alarm alarmaDeSonidoDecorator = new SoundAlarmDecorator(3, LocalDateTime.now(), alarmaDeEmailDecorator);

        //La alarma suena, y se disparan las 3 acciones al mismo tiempo.
        alarmaDeSonidoDecorator.ring();

*/

        Calendar calendario = new Calendar();
        calendario.addAppointment(new WholeDayTask("titulo", "description", LocalDate.now()));

        Alarm alarmDeNotif = new NotificationAlarm(1, LocalDateTime.now());
        calendario.addAlarmToAppointment(0, alarmDeNotif);

        calendario.removeAlarmFromAppointment(0, 1);
    }
}