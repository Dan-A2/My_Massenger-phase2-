package org.openjfx.listeners.massages;

import org.openjfx.controller.modelController.GroupController;
import org.openjfx.event.SendMassageToGroupEvent;

public class SendMassageGroupListenerImplementation implements SendMassageGroupListener{

    private GroupController controller = new GroupController();

    @Override
    public void listen(SendMassageToGroupEvent event) {
        controller.sendMassage(event.getGroupChat(), event.getMassage(), event.getSender());
    }
}