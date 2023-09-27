package org.openjfx.listeners.users;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.DeleteUserEvent;

public class DeleteUserListenerImplementation implements DeleteUserListener{

    private UserController controller = new UserController();

    @Override
    public void listen(DeleteUserEvent event) {
        controller.deleteUser(event.getUser());
    }

}