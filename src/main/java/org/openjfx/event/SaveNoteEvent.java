package org.openjfx.event;

import org.openjfx.models.User;

public class SaveNoteEvent {

    private User user;
    private String text;

    public SaveNoteEvent(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}