package org.openjfx.view.lists.chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class GroupForListView {

    @FXML
    private Label groupNameLabel;

    @FXML
    private Label unseenLabel;

    @FXML
    private Button viewBtn;

    @FXML
    private Button leaveBtn;

    @FXML
    void leave(ActionEvent event) {
        listener.listenCommand(COMMANDS.LEAVE);
    }

    @FXML
    void view(ActionEvent event) {
        listener.listenCommand(COMMANDS.VIEW);
    }

    private CommandListener listener;

    public void setListener(CommandListener listener) {
        this.listener = listener;
    }

    public Label getGroupNameLabel() {
        return groupNameLabel;
    }

    public Label getUnseenLabel() {
        return unseenLabel;
    }
}