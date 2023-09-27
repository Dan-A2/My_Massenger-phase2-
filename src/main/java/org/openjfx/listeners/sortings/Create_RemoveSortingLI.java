package org.openjfx.listeners.sortings;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.CreateSortingEvent;

public class Create_RemoveSortingLI implements Create_RemoveSortingListener{

    private UserController controller = new UserController();

    @Override
    public void listenCreate(CreateSortingEvent event) {
        controller.createSorting(event.getUser(), event.getSortingName(), event.getId());
    }

    @Override
    public void listenRemove(CreateSortingEvent event) {
        controller.removeSorting(event.getUser(), event.getSortingName(), event.getId());
    }
}