package org.openjfx.event;

import org.openjfx.models.Chat;
import org.openjfx.models.User;

public class ClearUnseenChatEvent {

    private Chat chat;
    private User user;

    public ClearUnseenChatEvent(Chat chat, User user) {
        this.chat = chat;
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public User getUser() {
        return user;
    }
}