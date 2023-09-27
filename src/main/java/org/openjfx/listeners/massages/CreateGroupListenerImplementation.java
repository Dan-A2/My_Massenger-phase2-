package org.openjfx.listeners.massages;

import org.openjfx.controller.modelController.GroupController;
import org.openjfx.event.CreateGroupEvent;

public class CreateGroupListenerImplementation implements CreateGroupListener{

    private GroupController controller = new GroupController();

    @Override
    public void listen(CreateGroupEvent event) {
        controller.createGroup(event.getUser(), event.getId(), event.getName());
    }
}