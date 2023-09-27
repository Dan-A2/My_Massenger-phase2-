package org.openjfx.event;

import org.openjfx.models.User;

public class SaveTweetEvent {

    private User saver;
    private int tweetId;

    public SaveTweetEvent(User saver, int tweetId) {
        this.saver = saver;
        this.tweetId = tweetId;
    }

    public User getSaver() {
        return saver;
    }

    public int getTweetId() {
        return tweetId;
    }
}
