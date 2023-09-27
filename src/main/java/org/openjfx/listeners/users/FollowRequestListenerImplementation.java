package org.openjfx.listeners.users;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.FollowRequestEvent;

public class FollowRequestListenerImplementation implements FollowRequestListener{

    private UserController controller = new UserController();

    @Override
    public void listenAddRequest(FollowRequestEvent event) {
        controller.addFollowRequest(event.getUser(), event.getRequester());
    }

    @Override
    public void listenRemoveRequest(FollowRequestEvent event) {
        controller.removeFollowRequest(event.getUser(), event.getRequester());
    }
}