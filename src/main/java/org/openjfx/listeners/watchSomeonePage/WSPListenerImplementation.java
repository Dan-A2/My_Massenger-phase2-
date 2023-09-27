package org.openjfx.listeners.watchSomeonePage;

import org.openjfx.controller.modelController.WatchUsersPageController;
import org.openjfx.event.WatchUserPageEvent;

public class WSPListenerImplementation implements WatchSomeonesPageListener{

    WatchUsersPageController controller = new WatchUsersPageController();

    @Override
    public void listen(WatchUserPageEvent event) {
        controller.goToPage(event.getShowFrom(), event.getShowTo());
    }

}
