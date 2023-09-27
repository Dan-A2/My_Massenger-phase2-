package org.openjfx.listeners.users;

import org.openjfx.controller.mainMenuController.RequestController;
import org.openjfx.event.DoRequestEvent;

public class DoRequestListenerImplementation implements DoRequestListener{

    private RequestController controller = new RequestController();

    @Override
    public void listenRequest(DoRequestEvent event) {
        switch (event.getCommand()) {
            case ACCEPT:
                controller.acceptRequest(event.getDoer(), event.getRequester());
                break;
            case IGNORE:
                controller.ignoreRequest(event.getDoer(), event.getRequester());
                break;
            case DENY:
                controller.denyRequest(event.getDoer(), event.getRequester());
                break;
        }
    }
}