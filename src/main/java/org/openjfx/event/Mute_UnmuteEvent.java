package org.openjfx.event;

import org.openjfx.models.User;

public class Mute_UnmuteEvent {

    private User doer;
    private int user2Id;

    public Mute_UnmuteEvent(User doer, int user2Id) {
        this.doer = doer;
        this.user2Id = user2Id;
    }

    public User getDoer() {
        return doer;
    }

    public int getUser2Id() {
        return user2Id;
    }
}