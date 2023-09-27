package org.openjfx.event;

import org.openjfx.models.GroupChat;
import org.openjfx.models.User;

public class ClearUnseenGroupEvent {

    private GroupChat chat;
    private User user;

    public ClearUnseenGroupEvent(GroupChat chat, User user) {
        this.chat = chat;
        this.user = user;
    }

    public GroupChat getChat() {
        return chat;
    }

    public User getUser() {
        return user;
    }
}