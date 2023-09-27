package org.openjfx.listeners.blocking;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.BlockEvent;

public class Block_UnblockListenerImplementation implements BlockUnblockListener{

    private UserController controller = new UserController();

    @Override
    public void listenBlock(BlockEvent event) {
        controller.blockUser(event.getUser1(), event.getUser2Id());
    }

    @Override
    public void listenUnblock(BlockEvent event) {
        controller.unblockUser(event.getUser1(), event.getUser2Id());
    }

}