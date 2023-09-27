package org.openjfx.event;

import org.openjfx.models.Tweet_Comment;
import org.openjfx.models.User;

public class CommentEvent {

    private String txt;
    private User sender;
    private Tweet_Comment tweetComment;

    public CommentEvent(String txt, User sender, Tweet_Comment tweetComment) {
        this.txt = txt;
        this.sender = sender;
        this.tweetComment = tweetComment;
    }

    public String getTxt() {
        return txt;
    }

    public User getSender() {
        return sender;
    }

    public Tweet_Comment getTweetComment() {
        return tweetComment;
    }
}