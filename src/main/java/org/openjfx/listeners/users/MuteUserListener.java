package org.openjfx.listeners.users;

import org.openjfx.event.Mute_UnmuteEvent;

public interface MuteUserListener {

    void listenMuter(Mute_UnmuteEvent event);
    void listenUnMuter(Mute_UnmuteEvent event);

}
