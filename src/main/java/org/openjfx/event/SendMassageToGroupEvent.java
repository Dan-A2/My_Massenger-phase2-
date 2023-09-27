package org.openjfx.event;

import org.openjfx.models.GroupChat;
import org.openjfx.models.Massage;
import org.openjfx.models.User;

public class SendMassageToGroupEvent {

    private GroupChat groupChat;
    private Massage massage;
    private User sender;

    public SendMassageToGroupEvent(GroupChat groupChat, Massage massage, User sender) {
        this.groupChat = groupChat;
        this.massage = massage;
        this.sender = sender;
    }

    public GroupChat getGroupChat() {
        return groupChat;
    }

    public Massage getMassage() {
        return massage;
    }

    public User getSender() {
        return sender;
    }
}