package org.openjfx.listeners.massages;

import org.openjfx.controller.modelController.ChatController;
import org.openjfx.controller.modelController.GroupController;
import org.openjfx.event.ClearUnseenChatEvent;
import org.openjfx.event.ClearUnseenGroupEvent;

public class UpdateUnseenMassagesListenerImplementation implements UpdateUnseenMassagesListener{

    private ChatController controller = new ChatController();
    private GroupController controller1 = new GroupController();

    @Override
    public void clearChat(ClearUnseenChatEvent event) {
        controller.resetMassages(event.getChat(), event.getUser());
    }

    @Override
    public void clearGroup(ClearUnseenGroupEvent event) {
        controller1.resetMassages(event.getUser(), event.getChat());
    }
}