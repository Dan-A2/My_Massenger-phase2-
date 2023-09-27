package org.openjfx.event;

import org.openjfx.models.Tweet_Comment;

public class ReportEvent {

    private Tweet_Comment tweet;

    public ReportEvent(Tweet_Comment tweet) {
        this.tweet = tweet;
    }

    public Tweet_Comment getTweet() {
        return tweet;
    }
}