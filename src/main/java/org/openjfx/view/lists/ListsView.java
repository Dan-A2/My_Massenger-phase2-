package org.openjfx.view.lists;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.openjfx.controller.Config;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class ListsView {

    @FXML
    private Label typeLabel;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vBox;

    @FXML
    private Button backBtn;

    @FXML
    private Button mainMenuBtn;

    @FXML
    void getBack(ActionEvent event) {
        listListener.listenCommand(COMMANDS.BACK);
    }

    @FXML
    void getBackMM(ActionEvent event) {
        listListener.listenCommand(COMMANDS.MAINMENU);
    }

    private CommandListener listListener;

    public void setListListener(CommandListener listListener) {
        this.listListener = listListener;
    }

    public VBox getvBox() {
        return vBox;
    }

    public Label getTypeLabel() {
        return typeLabel;
    }
}
