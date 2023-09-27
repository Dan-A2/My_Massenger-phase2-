package org.openjfx.view.timeline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.openjfx.controller.Config;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class TimelineView {

    @FXML
    private Button backBtn;

    @FXML
    private Button mainMenuBtn;

    @FXML
    private Button followingsTweetsBtn;

    @FXML
    private Button likedTweetsBtn;

    @FXML
    void getBack(ActionEvent event) {
        timelineListener.listenCommand(COMMANDS.BACK);
    }

    @FXML
    void getBackMM(ActionEvent event) {
        timelineListener.listenCommand(COMMANDS.MAINMENU);
    }

    @FXML
    void showFollowingTweet(ActionEvent event) {
        timelineListener.listenCommand(COMMANDS.SHOWFOLLOWINGTWEET);
    }

    @FXML
    void showLiked(ActionEvent event) {
        timelineListener.listenCommand(COMMANDS.SHOWLIKED);
    }

    private CommandListener timelineListener;

    public void setTimelineListener(CommandListener timelineListener) {
        this.timelineListener = timelineListener;
    }
}