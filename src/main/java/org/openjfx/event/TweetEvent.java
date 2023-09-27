package org.openjfx.event;

public class TweetEvent {

    private String text;
    private Integer senderId;
    private Integer imageId;

    public TweetEvent(String text, Integer senderId, Integer imageId) {
        this.text = text;
        this.senderId = senderId;
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public Integer getImageId() {
        return imageId;
    }
}
