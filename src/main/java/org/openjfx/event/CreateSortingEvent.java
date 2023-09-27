package org.openjfx.event;

import org.openjfx.models.User;

import java.util.LinkedList;

public class CreateSortingEvent {

    private LinkedList<Integer> id;
    private String sortingName;
    private User user;

    public CreateSortingEvent(LinkedList<Integer> id, String sortingName, User user) {
        this.id = id;
        this.sortingName = sortingName;
        this.user = user;
    }

    public LinkedList<Integer> getId() {
        return id;
    }

    public String getSortingName() {
        return sortingName;
    }

    public User getUser() {
        return user;
    }
}