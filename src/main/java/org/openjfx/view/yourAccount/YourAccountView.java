package org.openjfx.view.yourAccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

import javax.swing.*;

public class YourAccountView {

    @FXML
    private AnchorPane myAccountPane;

    @FXML
    private Button addTweetBtn;

    @FXML
    private Button myTweetsBtn;

    @FXML
    private Button followersBtn;

    @FXML
    private Button followingsBtn;

    @FXML
    private Button blockedBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button notificationsBtn;

    @FXML
    private Button sortingsBtn;

    @FXML
    private TextArea tweetArea;

    @FXML
    private Button postBtn;

    @FXML
    private Button requestersBtn;

    @FXML
    private Button attachBtn;

    @FXML
    void addTweetPressed(ActionEvent event) {
        tweetArea.setVisible(true);
        postBtn.setVisible(true);
        attachBtn.setVisible(true);
    }

    @FXML
    void blockedPressed(ActionEvent event) {
        yourAccountListener.listenCommand(COMMANDS.SHOWBLOCKED);
    }

    @FXML
    void followersPressed(ActionEvent event) {
        yourAccountListener.listenCommand(COMMANDS.SHOWFOLLOWERS);
    }

    @FXML
    void followingsPressed(ActionEvent event) {
        yourAccountListener.listenCommand(COMMANDS.SHOWFOLLOWINGS);
    }

    @FXML
    void myTweetsPressed(ActionEvent event) {
        yourAccountListener.listenCommand(COMMANDS.SHOWMYTWEETS);
    }

    @FXML
    void notificationsPressed(ActionEvent event) {
        yourAccountListener.listenCommand(COMMANDS.SHOWNOTIFS);
    }

    @FXML
    void sortingsPressed(ActionEvent event){
        yourAccountListener.listenCommand(COMMANDS.SHOWSORTINGS);
    }

    @FXML
    void postTweet(ActionEvent event) {
        yourAccountListener.listenCommand(COMMANDS.POSTTWEET);
    }

    @FXML
    void profilePressed(ActionEvent event) {
        yourAccountListener.listenCommand(COMMANDS.GOTOPROFILE);
    }

    @FXML
    void showRequests(ActionEvent event) {
        yourAccountListener.listenCommand(COMMANDS.SHOWREQUESTERS);
    }

    @FXML
    void attachPic(ActionEvent event) {
        yourAccountListener.listenCommand(COMMANDS.SELECTIMAGE);
    }

    public TextArea getTweetArea() {
        return tweetArea;
    }

    private CommandListener yourAccountListener;

    public void setYourAccountListener(CommandListener yourAccountListener) {
        this.yourAccountListener = yourAccountListener;
    }

    public Button getPostBtn() {
        return postBtn;
    }

    public Button getAttachBtn() {
        return attachBtn;
    }
}