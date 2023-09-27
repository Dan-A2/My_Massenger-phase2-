package org.openjfx.listeners.massages;

import org.openjfx.controller.modelController.SendingMassageController;
import org.openjfx.event.MassageEvent;

public class MassageListenerImplementation implements MassageListener{

    private SendingMassageController controller = new SendingMassageController();

    @Override
    public void listen(MassageEvent event) {
        controller.sendMassage1(event.getSender(), event.getReceiver(), event.getMassage(), false, event.getImageId());
    }
}