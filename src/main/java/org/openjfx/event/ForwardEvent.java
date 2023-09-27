package org.openjfx.event;

import org.openjfx.models.User;

public class ForwardEvent {

    private User forwardTo;
    private User forwardFrom;
    private String tweet;
    private int imageId;

    public ForwardEvent(User forwardTo, User forwardFrom, String tweet, int imageId) {
        this.forwardTo = forwardTo;
        this.tweet = tweet;
        this.forwardFrom = forwardFrom;
        this.imageId = imageId;
    }

    public User getForwardFrom() {
        return forwardFrom;
    }

    public User getForwardTo() {
        return forwardTo;
    }

    public String getTweetText() {
        return tweet;
    }

    public int getImageId() {
        return imageId;
    }
}