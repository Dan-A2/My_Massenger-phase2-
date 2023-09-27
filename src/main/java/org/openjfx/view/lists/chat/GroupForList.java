package org.openjfx.view.lists.chat;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.event.ClearUnseenGroupEvent;
import org.openjfx.event.GroupLeaveEvent;
import org.openjfx.listeners.CommandListener;
import org.openjfx.listeners.massages.GroupLeaveListenerImplementation;
import org.openjfx.listeners.massages.UpdateUnseenMassagesListenerImplementation;
import org.openjfx.models.GroupChat;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.chat.ChatFX;

public class GroupForList {

    private AnchorPane pane;

    public void create(User showTo, GroupChat group) {

        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(Config.getConfig("lists").getProperty(String.class,"groupForList")));
        try {
            pane = loader.load();
            GroupForListView view = loader.getController();
            view.getGroupNameLabel().setText(group.getGroupName());
            view.getUnseenLabel().setText(Integer.toString(showTo.getUnseenMassagesGroups().get(group.getId())));
            view.setListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS commands) {
                    if (commands == COMMANDS.LEAVE) {
                        GroupLeaveEvent event = new GroupLeaveEvent(showTo, group);
                        GroupLeaveListenerImplementation implementation = new GroupLeaveListenerImplementation();
                        implementation.listen(event);
                    } else if (commands == COMMANDS.VIEW) {
                        ChatFX chatFX = new ChatFX();
                        ClearUnseenGroupEvent event = new ClearUnseenGroupEvent(group, showTo);
                        UpdateUnseenMassagesListenerImplementation implementation = new UpdateUnseenMassagesListenerImplementation();
                        implementation.clearGroup(event);
                        chatFX.generateGroup(showTo, group);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public AnchorPane getPane() {
        return pane;
    }
}