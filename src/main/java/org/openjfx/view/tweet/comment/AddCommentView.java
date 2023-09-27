package org.openjfx.view.tweet.comment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

import javax.swing.*;

public class AddCommentView {

    @FXML
    private TextArea commentArea;

    @FXML
    private Button addCommentBtn;

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
    void addComment(ActionEvent event) {
        if(commentArea.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Really?");
        } else {
            JOptionPane.showMessageDialog(null, "Comment Added");
        }
        addCommentListener.listenCommand(COMMANDS.ADDCOMMENT);
    }

    @FXML
    void getBack(ActionEvent event) {
        addCommentListener.listenCommand(COMMANDS.BACK);
    }

    @FXML
    void getBackMM(ActionEvent event) {
        addCommentListener.listenCommand(COMMANDS.MAINMENU);
    }

    public TextArea getCommentArea() {
        return commentArea;
    }

    private CommandListener addCommentListener;

    public void setAddCommentListener(CommandListener addCommentListener) {
        this.addCommentListener = addCommentListener;
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