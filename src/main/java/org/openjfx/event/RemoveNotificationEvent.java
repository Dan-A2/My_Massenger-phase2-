package org.openjfx.event;

import org.openjfx.models.User;

public class RemoveNotificationEvent {

    private User user;

    public User getUser() {
        return user;
    }

    public RemoveNotificationEvent(User user) {
        this.user = user;
    }
}