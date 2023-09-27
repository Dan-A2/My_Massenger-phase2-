package org.openjfx.view.watchUserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class WatchUserPageView {

    @FXML
    private Circle profileCircle;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button followBtn1;

    @FXML
    private Button followBtn2;

    @FXML
    private Button unfollowBtn1;

    @FXML
    private Button unfollowBtn2;

    @FXML
    private Label followersNum;

    @FXML
    private Label followingsNum;

    @FXML
    private Label tweetsNum;

    @FXML
    private Label lastSeenLabel;

    @FXML
    private Label firstnameLabel;

    @FXML
    private Label lastnameLabel;

    @FXML
    private Button backBtn;

    @FXML
    private Button blockBtn;

    @FXML
    private Button unblockBtn;

    @FXML
    private Button muteBtn;

    @FXML
    private Button unmuteBtn;

    @FXML
    private Button sendMassageBtn;

    @FXML
    private Button mainMenuBtn;

    @FXML
    private Button requestedBtn;

    @FXML
    void blockUser(ActionEvent event) {
        wListener.listenCommand(COMMANDS.BLOCK);
    }

    @FXML
    void followPressed(ActionEvent event) {
        wListener.listenCommand(COMMANDS.FOLLOW);
    }

    @FXML
    void getBack(ActionEvent event) {
        wListener.listenCommand(COMMANDS.BACK);
    }

    @FXML
    void getBackMM(ActionEvent event) {
        wListener.listenCommand(COMMANDS.MAINMENU);
    }

    @FXML
    void mouseChangeFollowP(MouseEvent event) {
        wListener.listenCommand(COMMANDS.MOUSECHANGEFOLLOWP);
    }

    @FXML
    void mouseChangeUnfollowP(MouseEvent event) {
        wListener.listenCommand(COMMANDS.MOUSECHANGEUNFOLLOWP);
    }

    @FXML
    void mouseChangeFollowN(MouseEvent event) {
        wListener.listenCommand(COMMANDS.MOUSECHANGEFOLLOWN);
    }

    @FXML
    void mouseChangeUnfollowN(MouseEvent event) {
        wListener.listenCommand(COMMANDS.MOUSECHANGEUNFOLLOWN);
    }

    @FXML
    void muteUser(ActionEvent event) {
        wListener.listenCommand(COMMANDS.MUTE);
    }

    @FXML
    void showFollowers(MouseEvent event) {
        wListener.listenCommand(COMMANDS.SHOWFOLLOWERS);
    }

    @FXML
    void showFollowings(MouseEvent event) {
        wListener.listenCommand(COMMANDS.SHOWFOLLOWINGS);
    }

    @FXML
    void showTweets(MouseEvent event) {
        wListener.listenCommand(COMMANDS.SHOWTWEETS);
    }

    @FXML
    void unblockUser(ActionEvent event) {
        wListener.listenCommand(COMMANDS.UNBLOCK);
    }

    @FXML
    void unfollowPressed(ActionEvent event) {
        wListener.listenCommand(COMMANDS.UNFOLLOW);
    }

    @FXML
    void unmuteUser(ActionEvent event) {
        wListener.listenCommand(COMMANDS.UNMUTE);
    }

    @FXML
    void removeRequest(ActionEvent event) {
        wListener.listenCommand(COMMANDS.REMOVEREQUEST);
    }

    @FXML
    void sendMassage(ActionEvent event) {
        wListener.listenCommand(COMMANDS.SENDMASSAGE);
    }

    private CommandListener wListener;

    public void setListener(CommandListener WUPListener) {
        this.wListener = WUPListener;
    }

    public Label getFollowersNum() {
        return followersNum;
    }

    public Label getFollowingsNum() {
        return followingsNum;
    }

    public Label getTweetsNum() {
        return tweetsNum;
    }

    public Label getLastSeenLabel() {
        return lastSeenLabel;
    }

    public Label getFirstnameLabel() {
        return firstnameLabel;
    }

    public Label getLastnameLabel() {
        return lastnameLabel;
    }

    public Circle getProfileCircle() {
        return profileCircle;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public Button getFollowBtn1() {
        return followBtn1;
    }

    public Button getFollowBtn2() {
        return followBtn2;
    }

    public Button getUnfollowBtn1() {
        return unfollowBtn1;
    }

    public Button getUnfollowBtn2() {
        return unfollowBtn2;
    }

    public Button getBlockBtn() {
        return blockBtn;
    }

    public Button getUnblockBtn() {
        return unblockBtn;
    }

    public Button getMuteBtn() {
        return muteBtn;
    }

    public Button getUnmuteBtn() {
        return unmuteBtn;
    }

    public Button getSendMassageBtn() {
        return sendMassageBtn;
    }

    public Button getRequestedBtn() {
        return requestedBtn;
    }
}