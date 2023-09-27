package org.openjfx.event;

import org.openjfx.models.User;

public class DeleteUserEvent {

    private User user;

    public DeleteUserEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}