package org.openjfx.event;

import org.openjfx.models.Massage;

public class EditMassageEvent {

    private Massage massage;
    private String text;

    public EditMassageEvent(Massage massage, String text) {
        this.massage = massage;
        this.text = text;
    }

    public Massage getMassage() {
        return massage;
    }

    public String getText() {
        return text;
    }
}