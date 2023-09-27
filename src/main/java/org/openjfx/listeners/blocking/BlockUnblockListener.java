package org.openjfx.listeners.blocking;

import org.openjfx.event.BlockEvent;

public interface BlockUnblockListener {

    void listenBlock(BlockEvent event);
    void listenUnblock(BlockEvent event);

}
