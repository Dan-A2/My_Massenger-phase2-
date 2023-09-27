package org.openjfx.view.lists.user.forward;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.openjfx.controller.Config;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class UserForListForwardView {

    @FXML
    private Label usernameLabel;

    @FXML
    private Button forwardBtn;

    @FXML
    void forward(ActionEvent event) {
        listener.listenCommand(COMMANDS.FORWARD);
    }

    private CommandListener listener;

    public void setListener(CommandListener listener) {
        this.listener = listener;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }
}