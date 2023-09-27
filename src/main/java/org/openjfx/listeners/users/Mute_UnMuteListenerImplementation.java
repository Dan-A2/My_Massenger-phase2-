package org.openjfx.listeners.users;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.Mute_UnmuteEvent;

public class Mute_UnMuteListenerImplementation implements MuteUserListener{

    private UserController controller = new UserController();

    @Override
    public void listenMuter(Mute_UnmuteEvent event) {
        controller.mute(event.getDoer(), event.getUser2Id());
    }

    @Override
    public void listenUnMuter(Mute_UnmuteEvent event) {
        controller.unMute(event.getDoer(), event.getUser2Id());
    }

}