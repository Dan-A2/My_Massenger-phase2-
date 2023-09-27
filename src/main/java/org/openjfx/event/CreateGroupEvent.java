package org.openjfx.event;

import org.openjfx.models.User;

import java.util.LinkedList;

public class CreateGroupEvent {

    private User user; // creator
    private LinkedList<Integer> id;
    private String name;

    public CreateGroupEvent(User user, LinkedList<Integer> id, String name) {
        this.user = user;
        this.id = id;
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public LinkedList<Integer> getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}