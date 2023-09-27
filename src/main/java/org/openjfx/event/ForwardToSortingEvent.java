package org.openjfx.event;

import org.openjfx.models.User;

public class ForwardToSortingEvent {

    private String sortingName;
    private User forwardFrom;
    private String tweet;
    private boolean isForwarded;
    private int imageId;

    public ForwardToSortingEvent(String sortingName, User forwardFrom, String tweet, boolean isForwarded, int imageId) {
        this.sortingName = sortingName;
        this.forwardFrom = forwardFrom;
        this.tweet = tweet;
        this.isForwarded = isForwarded;
        this.imageId = imageId;
    }

    public String getSortingName() {
        return sortingName;
    }

    public User getForwardFrom() {
        return forwardFrom;
    }

    public String getTweet() {
        return tweet;
    }

    public boolean isForwarded() {
        return isForwarded;
    }

    public int getImageId() {
        return imageId;
    }
}