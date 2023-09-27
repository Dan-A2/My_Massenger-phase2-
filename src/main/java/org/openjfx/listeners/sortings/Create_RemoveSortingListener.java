package org.openjfx.listeners.sortings;

import org.openjfx.event.CreateSortingEvent;

public interface Create_RemoveSortingListener {

    void listenCreate(CreateSortingEvent event);
    void listenRemove(CreateSortingEvent event);

}