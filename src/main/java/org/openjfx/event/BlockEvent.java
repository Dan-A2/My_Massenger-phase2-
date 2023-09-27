package org.openjfx.event;

import org.openjfx.models.User;

public class BlockEvent {

    private User user1;
    private int user2Id;

    public BlockEvent(User user1, int user2Id) {
        this.user1 = user1;
        this.user2Id = user2Id;
    }

    public User getUser1() {
        return user1;
    }

    public int getUser2Id() {
        return user2Id;
    }
}