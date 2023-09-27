package org.openjfx.listeners.massages;

import org.openjfx.controller.modelController.SendingMassageController;
import org.openjfx.event.ForwardToSortingEvent;

public class ForwardToSortingListenerImplementation implements ForwardToSortingListener{

    private SendingMassageController controller = new SendingMassageController();

    @Override
    public void listen(ForwardToSortingEvent event) {
        controller.sendMassageSorting(event.getForwardFrom(), event.getSortingName(), event.getTweet(), event.isForwarded(), event.getImageId());
    }

}