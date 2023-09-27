package org.openjfx.view.lists.user.select;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class UserForListSelectView {

    @FXML
    private Label usernameLabel;

    @FXML
    private Button selectBtn;

    @FXML
    private Button deselectBtn;

    @FXML
    void deselect(ActionEvent event) {
        listener.listenCommand(COMMANDS.DESELECT);
    }

    @FXML
    void select(ActionEvent event) {
        listener.listenCommand(COMMANDS.SELECT);
    }

    private CommandListener listener;

    public void setListener(CommandListener listener) {
        this.listener = listener;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public Button getSelectBtn() {
        return selectBtn;
    }

    public Button getDeselectBtn() {
        return deselectBtn;
    }
}