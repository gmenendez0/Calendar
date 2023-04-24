package org.calendar;

import org.calendar.alarms.*;

public class Main {
    public static void main(String[] args) {
        //El usuario viene y dice que quiere agregarle a X appointment una alarma de notification.
        Alarm alarmDeNotif = new NotificationAlarm();

        //Luego dice que quiere que la misma alarma le envie un email, además de la notification anterior.
        Alarm alarmaDeEmailDecorator = new EmailAlarmDecorator(alarmDeNotif);
        //alarmaDeEmailDecorator.ring();

        //Por último dice que quiere que la misma alarma le emita un sonido, además de la notification y el email anterior.
        Alarm alarmaDeSonidoDecorator = new SoundAlarmDecorator(alarmaDeEmailDecorator);
        alarmaDeSonidoDecorator.ring();
    }
}