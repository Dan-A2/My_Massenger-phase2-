package org.openjfx.event;

import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;

public class DoRequestEvent {

    private User doer;
    private User requester;
    private COMMANDS command;

    public DoRequestEvent(User doer, User requester, COMMANDS command) {
        this.doer = doer;
        this.requester = requester;
        this.command = command;
    }

    public User getDoer() {
        return doer;
    }

    public User getRequester() {
        return requester;
    }

    public COMMANDS getCommand() {
        return command;
    }
}