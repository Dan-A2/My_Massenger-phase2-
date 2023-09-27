package org.openjfx.view.tweet;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class TweetComponentView {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane mainPane;

    @FXML
    private Circle circle;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label tweetLabel;

    @FXML
    private FontAwesomeIconView likeBtn;

    @FXML
    private FontAwesomeIconView saveBtn;

    @FXML
    private FontAwesomeIconView unsaveBtn;

    @FXML
    private FontAwesomeIconView resendBtn;

    @FXML
    private FontAwesomeIconView likedBtn;

    @FXML
    private FontAwesomeIconView forwardBtn;

    @FXML
    private FontAwesomeIconView blockBtn;

    @FXML
    private FontAwesomeIconView blockedBtn;

    @FXML
    private FontAwesomeIconView muteBtn;

    @FXML
    private FontAwesomeIconView mutedBtn;

    @FXML
    private FontAwesomeIconView reportBtn;

    @FXML
    private FontAwesomeIconView addCommentBtn;

    @FXML
    private FontAwesomeIconView showCommentsBtn;

    @FXML
    private Label likesLabel;

    @FXML
    private Label commentsLabel;

    @FXML
    void addComment(MouseEvent event) {
        listener.listenCommand(COMMANDS.ADDCOMMENT);
    }

    @FXML
    void block(MouseEvent event) {
        listener.listenCommand(COMMANDS.BLOCK);
    }

    @FXML
    void dislike(MouseEvent event) {
        listener.listenCommand(COMMANDS.DISLIKE);
    }

    @FXML
    void forward(MouseEvent event) {
        listener.listenCommand(COMMANDS.FORWARD);
    }

    @FXML
    void like(MouseEvent event) {
        listener.listenCommand(COMMANDS.LIKE);
    }

    @FXML
    void mute(MouseEvent event) {
        listener.listenCommand(COMMANDS.MUTE);
    }

    @FXML
    void report(MouseEvent event) {
        listener.listenCommand(COMMANDS.REPORT);
    }

    @FXML
    void resend(MouseEvent event) {
        listener.listenCommand(COMMANDS.RESEND);
    }

    @FXML
    void saveTweet(MouseEvent event) {
        listener.listenCommand(COMMANDS.SAVE);
    }

    @FXML
    void showComments(MouseEvent event) {
        listener.listenCommand(COMMANDS.SHOWCOMMENTS);
    }

    @FXML
    void unblock(MouseEvent event) {
        listener.listenCommand(COMMANDS.UNBLOCK);
    }

    @FXML
    void unmute(MouseEvent event) {
        listener.listenCommand(COMMANDS.UNMUTE);
    }

    @FXML
    void unsaveTweet(MouseEvent event) {
        listener.listenCommand(COMMANDS.UNSAVE);
    }

    private CommandListener listener;

    public void setListener(CommandListener listener) {
        this.listener = listener;
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

    public FontAwesomeIconView getLikeBtn() {
        return likeBtn;
    }

    public FontAwesomeIconView getBlockBtn() {
        return blockBtn;
    }

    public FontAwesomeIconView getBlockedBtn() {
        return blockedBtn;
    }

    public FontAwesomeIconView getMuteBtn() {
        return muteBtn;
    }

    public FontAwesomeIconView getMutedBtn() {
        return mutedBtn;
    }

    public FontAwesomeIconView getLikedBtn() {
        return likedBtn;
    }

    public Label getLikesLabel() {
        return likesLabel;
    }

    public Label getCommentsLabel() {
        return commentsLabel;
    }

    public FontAwesomeIconView getSaveBtn() {
        return saveBtn;
    }

    public FontAwesomeIconView getUnsaveBtn() {
        return unsaveBtn;
    }
}