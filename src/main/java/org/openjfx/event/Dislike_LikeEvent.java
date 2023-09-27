package org.openjfx.event;

import org.openjfx.models.User;

public class Dislike_LikeEvent {

    private User despicable;
    private int tweetId;

    public Dislike_LikeEvent(User despicable, int tweetId) {
        this.despicable = despicable;
        this.tweetId = tweetId;
    }

    public User getDespicable() {
        return despicable;
    }

    public int getTweetId() {
        return tweetId;
    }
}