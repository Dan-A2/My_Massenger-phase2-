package org.openjfx.models;

import com.google.gson.annotations.Expose;
import org.openjfx.controller.saveLoad.SaveNLoad;

import java.util.LinkedList;

public class Chat {

    private static int lastId = 0;
    private int ID;
    private final Integer user1id;
    private final Integer user2id;
    private final LinkedList<Integer> massagesId;
    @Expose(serialize = false, deserialize = false)
    private static final LinkedList<Chat> ALL_CHAT_MODELS = new LinkedList<>();
    int user1UnseenMassages;
    int user2UnseenMassages;

    public Chat(Integer user1id, Integer user2id) {
        this.user1id = user1id;
        this.user2id = user2id;
        this.massagesId = new LinkedList<>();
        user1UnseenMassages = 0;
        user2UnseenMassages = 0;
        lastId++;
        this.ID = lastId;
        ALL_CHAT_MODELS.add(this);
        SaveNLoad.saveChats();
    }

    public User getUser1() {
        for (User user : User.getActiveUsers()) {
            if(user.getId() == user1id && user.isActive()){
                return user;
            }
        }
        return null;
    }

    public User getUser2() {
        for (User user : User.getActiveUsers()) {
            if(user.getId() == user2id && user.isActive()){
                return user;
            }
        }
        return null;
    }

    public LinkedList<Integer> getMassagesId() {
        return massagesId;
    }

    public static LinkedList<Chat> getAllChats() {
        return ALL_CHAT_MODELS;
    }

    public int getUser1UnseenMassages() {
        return user1UnseenMassages;
    }

    public void setUser1UnseenMassages(int user1UnseenMassages) {
        this.user1UnseenMassages = user1UnseenMassages;
    }

    public int getUser2UnseenMassages() {
        return user2UnseenMassages;
    }

    public void setUser2UnseenMassages(int user2UnseenMassages) {
        this.user2UnseenMassages = user2UnseenMassages;
    }

    public static void setLastId(int lastId) {
        Chat.lastId = lastId;
    }

    public int getID() {
        return ID;
    }

}