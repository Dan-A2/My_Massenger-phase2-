package org.openjfx.event;

public class WatchUserPageEvent {

    private String showTo;
    private String showFrom;

    public WatchUserPageEvent(String showTO, String showFrom) {
        this.showTo = showTO;
        this.showFrom = showFrom;
    }

    public String getShowTo() {
        return showTo;
    }

    public String getShowFrom() {
        return showFrom;
    }
}
