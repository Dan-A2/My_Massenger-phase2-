package org.openjfx.view.lists.user.requester;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class UserForListRequestView {

    @FXML
    private Label usernameLabel;

    @FXML
    private Button acceptBtn;

    @FXML
    private Button ignoreBtn;

    @FXML
    private Button denyBtn;

    @FXML
    void acceptRequest(ActionEvent event) {
        listener.listenCommand(COMMANDS.ACCEPT);
    }

    @FXML
    void ignoreRequest(ActionEvent event) {
        listener.listenCommand(COMMANDS.IGNORE);
    }

    @FXML
    void denyRequest(ActionEvent event) {
        listener.listenCommand(COMMANDS.DENY);
    }

    private CommandListener listener;

    public void setListener(CommandListener listener) {
        this.listener = listener;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }
}