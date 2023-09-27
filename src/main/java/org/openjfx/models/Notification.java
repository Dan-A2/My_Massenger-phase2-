package org.openjfx.models;

import org.openjfx.controller.Config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notification {

    private String notif;
    private String dateTime;

    public Notification(String notif) {
        this.notif = notif;
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Config.getConfig("yourAccount").getProperty(String.class,"dateTimeFormat1"));
        String time1 = time.format(formatter);
        this.dateTime = time1;
    }

    public String getNotif() {
        return notif;
    }

    public String getDateTime() {
        return dateTime;
    }

}