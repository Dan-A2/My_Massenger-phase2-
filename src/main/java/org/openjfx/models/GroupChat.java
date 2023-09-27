package org.openjfx.models;

import com.google.gson.annotations.Expose;
import org.openjfx.controller.saveLoad.SaveNLoad;

import java.util.LinkedList;

public class GroupChat {

    private static int lastID = 0;
    private LinkedList<Integer> usersId;
    private String groupName;
    private LinkedList<Integer> massagesId;
    private int id;
    @Expose(serialize = false, deserialize = false)
    private static LinkedList<GroupChat> allGroups = new LinkedList<>();

    public GroupChat(String groupName) {
        this.groupName = groupName;
        usersId = new LinkedList<>();
        lastID++;
        this.id = lastID;
        massagesId = new LinkedList<>();
        allGroups.add(this);
        SaveNLoad.saveGroups();
    }

    public LinkedList<Integer> getUsersId() {
        return usersId;
    }

    public String getGroupName() {
        return groupName;
    }

    public LinkedList<Integer> getMassages() {
        return massagesId;
    }

    public int getId() {
        return id;
    }

    public static LinkedList<GroupChat> getAllGroups() {
        return allGroups;
    }

    public static void setLastID(int lastID) {
        GroupChat.lastID = lastID;
    }

    public void setUsersId(LinkedList<Integer> usersId) {
        this.usersId = usersId;
    }
}