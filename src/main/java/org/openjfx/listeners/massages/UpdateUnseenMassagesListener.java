package org.openjfx.listeners.massages;

import org.openjfx.event.ClearUnseenChatEvent;
import org.openjfx.event.ClearUnseenGroupEvent;

public interface UpdateUnseenMassagesListener {
    void clearChat(ClearUnseenChatEvent event);
    void clearGroup(ClearUnseenGroupEvent event);
}