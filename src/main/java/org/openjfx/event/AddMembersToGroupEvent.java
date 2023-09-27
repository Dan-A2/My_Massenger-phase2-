package org.openjfx.event;

import org.openjfx.models.GroupChat;

import java.util.LinkedList;

public class AddMembersToGroupEvent {

    private GroupChat chat;
    private LinkedList<Integer> id;

    public AddMembersToGroupEvent(GroupChat chat, LinkedList<Integer> id) {
        this.chat = chat;
        this.id = id;
    }

    public GroupChat getChat() {
        return chat;
    }

    public LinkedList<Integer> getId() {
        return id;
    }
}