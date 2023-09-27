package org.openjfx.event;

import org.openjfx.models.GroupChat;
import org.openjfx.models.User;

public class GroupLeaveEvent {

    private User user;
    private GroupChat groupChat;

    public GroupLeaveEvent(User user, GroupChat groupChat) {
        this.user = user;
        this.groupChat = groupChat;
    }

    public User getUser() {
        return user;
    }

    public GroupChat getGroupChat() {
        return groupChat;
    }
}