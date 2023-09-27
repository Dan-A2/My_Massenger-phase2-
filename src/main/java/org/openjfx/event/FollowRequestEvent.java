package org.openjfx.event;

import org.openjfx.models.User;

public class FollowRequestEvent {

    private User user;
    private User requester;

    public FollowRequestEvent(User user, User requester) {
        this.user = user;
        this.requester = requester;
    }

    public User getUser() {
        return user;
    }

    public User getRequester() {
        return requester;
    }
}