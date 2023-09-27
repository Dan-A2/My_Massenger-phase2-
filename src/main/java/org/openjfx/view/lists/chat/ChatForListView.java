package org.openjfx.view.lists.chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.openjfx.controller.Config;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class ChatForListView {

    @FXML
    private Label chatLabel;

    @FXML
    private Label unseenChatsLabel;

    @FXML
    private Button viewBtn;

    @FXML
    void view(ActionEvent event) {
        chatListListener.listenCommand(COMMANDS.VIEW);
    }

    private CommandListener chatListListener;

    public void setChatListListener(CommandListener chatListListener) {
        this.chatListListener = chatListListener;
    }

    public Label getChatLabel() {
        return chatLabel;
    }

    public Label getUnseenChatsLabel() {
        return unseenChatsLabel;
    }

    public Button getViewBtn() {
        return viewBtn;
    }
}