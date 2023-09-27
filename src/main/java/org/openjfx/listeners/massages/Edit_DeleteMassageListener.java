package org.openjfx.listeners.massages;

import org.openjfx.event.DeleteMassageEvent;
import org.openjfx.event.EditMassageEvent;

public interface Edit_DeleteMassageListener {
    void listenEdit(EditMassageEvent event);
    void listenDeleteTotally(DeleteMassageEvent event);
    void listenDeleteForUser(DeleteMassageEvent event);
}