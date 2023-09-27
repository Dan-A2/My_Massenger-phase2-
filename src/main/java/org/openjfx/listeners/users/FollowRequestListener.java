package org.openjfx.listeners.users;

import org.openjfx.event.FollowRequestEvent;

public interface FollowRequestListener {
    void listenAddRequest(FollowRequestEvent event);
    void listenRemoveRequest(FollowRequestEvent event);
}