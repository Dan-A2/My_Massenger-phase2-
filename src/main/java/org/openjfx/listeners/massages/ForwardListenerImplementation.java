package org.openjfx.listeners.massages;

import org.openjfx.controller.modelController.SendingMassageController;
import org.openjfx.event.ForwardEvent;

public class ForwardListenerImplementation implements ForwardListener{

    private SendingMassageController controller = new SendingMassageController();

    @Override
    public void listenForward(ForwardEvent event) {
        controller.sendMassage1(event.getForwardFrom(), event.getForwardTo(), event.getTweetText(), true, event.getImageId());
    }

}