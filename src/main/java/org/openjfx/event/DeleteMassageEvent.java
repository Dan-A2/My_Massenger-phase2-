package org.openjfx.event;

import org.openjfx.models.Massage;
import org.openjfx.models.User;

public class DeleteMassageEvent {

    private Massage massage;
    private User user;

    public DeleteMassageEvent(Massage massage, User user) {
        this.massage = massage;
        this.user = user;
    }

    public Massage getMassage() {
        return massage;
    }

    public User getUser() {
        return user;
    }
}