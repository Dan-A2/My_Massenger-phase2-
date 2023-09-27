package org.openjfx.listeners.massages;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.SaveMassageEvent;

public class SaveMassageListenerImplementation implements SaveMassageListener{

    private UserController controller = new UserController();

    @Override
    public void listen(SaveMassageEvent event) {
        controller.saveMassage(event.getSaver(), event.getMassage());
    }
}