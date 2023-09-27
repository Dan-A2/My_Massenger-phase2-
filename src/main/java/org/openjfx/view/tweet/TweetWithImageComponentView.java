package org.openjfx.view.tweet;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class TweetWithImageComponentView extends TweetComponentView {

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
    private FontAwesomeIconView unsaveBtn;

    @FXML
    private ImageView tweetImage;

    public ImageView getTweetImage() {
        return tweetImage;
    }

}