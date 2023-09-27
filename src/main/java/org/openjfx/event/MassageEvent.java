package org.openjfx.event;

import org.openjfx.models.User;

public class MassageEvent {

    private User sender;
    private User receiver;
    private String massage;
    private int imageId;

    public MassageEvent(User sender, User receiver, String massage, int imageId) {
        this.sender = sender;
        this.receiver = receiver;
        this.massage = massage;
        this.imageId = imageId;
    }

    public User getSender() {
        return sender;
    }

    public String getMassage() {
        return massage;
    }

    public User getReceiver() {
        return receiver;
    }

    public int getImageId() {
        return imageId;
    }
}