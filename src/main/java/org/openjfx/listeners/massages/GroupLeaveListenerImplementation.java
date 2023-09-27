package org.openjfx.listeners.massages;

import org.openjfx.controller.modelController.GroupController;
import org.openjfx.event.GroupLeaveEvent;

public class GroupLeaveListenerImplementation implements GroupLeaveListener{

    private GroupController controller = new GroupController();

    @Override
    public void listen(GroupLeaveEvent event) {
        controller.leavingUser(event.getUser(), event.getGroupChat());
    }
}