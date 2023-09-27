package org.openjfx.view.tweet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class TweetPageView {

    @FXML
    private GridPane gridPane;

    @FXML
    private Button backBtn;

    @FXML
    private Button mainMenuBtn;

    @FXML
    void getBack(ActionEvent event) {
        listener.listenCommand(COMMANDS.BACK);
    }

    @FXML
    void getBackMM(ActionEvent event) {
        listener.listenCommand(COMMANDS.MAINMENU);
    }

    private CommandListener listener;

    public void setListener(CommandListener listener) {
        this.listener = listener;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Button getBackBtn() {
        return backBtn;
    }
}