package org.openjfx.models;

import com.google.gson.annotations.Expose;
import org.openjfx.controller.Config;
import org.openjfx.controller.saveLoad.SaveNLoad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Massage {

    private static int lastId = 0;
    private int ID;
    private String massage;
    private String dateTime;
    private boolean isForwarded;
    private Integer imageId;
    private Integer senderID;
    @Expose(serialize = false, deserialize = false)
    private static LinkedList<Massage> allMassages = new LinkedList<>();

    public Massage(String massage, Integer senderId){
        this.massage = massage;
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Config.getConfig("yourAccount").getProperty(String.class,"dateTimeFormat1"));
        String lastSeen = time.format(formatter);
        this.dateTime = lastSeen;
        this.senderID = senderId;
        lastId++;
        this.ID = lastId;
        allMassages.add(this);
        SaveNLoad.saveMassages();
    }

    public String getMassage() {
        return massage;
    }

    public String getDateTime() {
        return dateTime;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        Massage.lastId = lastId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static LinkedList<Massage> getAllMassages() {
        return allMassages;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Integer getSenderID() {
        return senderID;
    }

    public boolean isForwarded() {
        return isForwarded;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public void setForwarded(boolean forwarded) {
        isForwarded = forwarded;
    }
}