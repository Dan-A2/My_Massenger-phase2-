package org.openjfx.listeners.users;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.RemoveNotificationEvent;

public class RemoveNotifListenerImplementation implements RemoveNotifListener{

    private UserController controller = new UserController();

    @Override
    public void listenRemove(RemoveNotificationEvent event) {
        controller.clearNotifications(event.getUser());
    }
}
