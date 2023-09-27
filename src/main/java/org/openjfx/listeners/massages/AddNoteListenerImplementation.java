package org.openjfx.listeners.massages;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.SaveNoteEvent;

public class AddNoteListenerImplementation implements AddNoteListener {

    private UserController controller = new UserController();

    @Override
    public void listen(SaveNoteEvent event) {
        controller.saveNote(event.getUser(), event.getText());
    }
}