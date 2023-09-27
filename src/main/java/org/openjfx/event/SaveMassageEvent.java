package org.openjfx.event;

import org.openjfx.models.Massage;
import org.openjfx.models.User;

public class SaveMassageEvent {

    private User saver;
    private Massage massage;

    public User getSaver() {
        return saver;
    }

    public Massage getMassage() {
        return massage;
    }

    public SaveMassageEvent(User saver, Massage massage) {
        this.saver = saver;
        this.massage = massage;
    }
}