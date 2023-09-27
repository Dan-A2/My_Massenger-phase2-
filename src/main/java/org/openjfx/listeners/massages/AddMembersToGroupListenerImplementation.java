package org.openjfx.listeners.massages;

import org.openjfx.controller.modelController.GroupController;
import org.openjfx.event.AddMembersToGroupEvent;

public class AddMembersToGroupListenerImplementation implements AddMembersToGroupListener{

    private GroupController controller = new GroupController();

    @Override
    public void listen(AddMembersToGroupEvent event) {
        controller.addMembers(event.getChat(), event.getId());
    }
}
