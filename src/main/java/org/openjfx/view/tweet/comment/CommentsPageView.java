package org.openjfx.view.tweet.comment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class CommentsPageView {

    @FXML
    private Circle circle;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label tweetLabel;

    @FXML
    private Button backBtn;

    @FXML
    private Button mainMenuBtn;

    @FXML
    private VBox vBox;

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

    public VBox getVBox() {
        return vBox;
    }

    public Circle getCircle() {
        return circle;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public Label getTweetLabel() {
        return tweetLabel;
    }
}