package org.openjfx.view.tweet;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.ImageController;
import org.openjfx.controller.modelController.Tweet_CommentController;
import org.openjfx.event.*;
import org.openjfx.listeners.*;
import org.openjfx.listeners.blocking.Block_UnblockListenerImplementation;
import org.openjfx.listeners.tweetNComment.AddTweetListenerImplementation;
import org.openjfx.listeners.tweetNComment.Dislike_LikeListenerImplementation;
import org.openjfx.listeners.tweetNComment.SaveTweetListenerImplementation;
import org.openjfx.listeners.users.Mute_UnMuteListenerImplementation;
import org.openjfx.listeners.users.ReportListenerImplementation;
import org.openjfx.models.Tweet_Comment;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.mainMenu.Mainmenu;
import org.openjfx.view.tweet.comment.AddComment;
import org.openjfx.view.tweet.comment.CommentsPage;
import org.openjfx.view.tweet.forward.Forward;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TweetComponent {

    static private final Logger logger = (Logger) LogManager.getLogger(TweetComponent.class);
    private AnchorPane pane;
    private Tweet_Comment tweet_comment;
    private User currentUser = Mainmenu.getMenuView().getCurrentUser();

    public void generate(Tweet_Comment tweet) {

        tweet_comment = tweet;
        FXMLLoader loader1 = new FXMLLoader(SceneManager.class.getResource(Config.getConfig("tweet").getProperty(String.class,"tweetAddress")));
        FXMLLoader loader2 = new FXMLLoader(SceneManager.class.getResource(Config.getConfig("tweet").getProperty(String.class,"tweetWithImageAddress")));
        try {
            if (tweet.getImageId() != null) {
                pane = loader2.load();
            } else {
                pane = loader1.load();
            }
            logger.info(Config.getConfig("tweet").getProperty(String.class,"tweetInfo"));
        } catch (Exception e) {
            logger.warn(Config.getConfig("tweet").getProperty(String.class,"tweetWarn"));
            e.printStackTrace();
        }
        Tweet_CommentController controller = new Tweet_CommentController();
        User sender = controller.getSender(tweet_comment);
        ImageController controller1 = new ImageController();
        if (tweet.getImageId() != null) {
            TweetWithImageComponentView view = loader2.getController();
            view.getTweetLabel().setText(tweet.getText());
            setListener(view);
            view.getUsernameLabel().setText(sender.getUsername());
            if (sender.getProfileImageId() != null) {
                view.getCircle().setFill(new ImagePattern(controller1.getImage(sender.getProfileImageId())));
            }
            update(view);
            updateImage(view);
        } else {
            TweetComponentView view = loader1.getController();
            view.getTweetLabel().setText(tweet.getText());
            if (sender.getProfileImageId() != null) {
                view.getCircle().setFill(new ImagePattern(controller1.getImage(sender.getProfileImageId())));
            }
            setListener(view);
            view.getUsernameLabel().setText(controller.getSender(tweet_comment).getUsername());
            update(view);
        }

    }

    public AnchorPane getPane() {
        return pane;
    }

    private void setListener(TweetComponentView tweetComponentView){

        tweetComponentView.setListener(new CommandListener() {
            @Override
            public void listenCommand(COMMANDS command) {
                if(command == COMMANDS.ADDCOMMENT){
                    AddComment addComment = new AddComment();
                    addComment.show(currentUser, tweet_comment);
                    update(tweetComponentView);
                } else if(command == COMMANDS.BLOCK){
                    BlockEvent event = new BlockEvent(currentUser, tweet_comment.getSenderID());
                    Block_UnblockListenerImplementation implementation = new Block_UnblockListenerImplementation();
                    implementation.listenBlock(event);
                    update(tweetComponentView);
                } else if(command == COMMANDS.UNBLOCK){
                    BlockEvent event = new BlockEvent(currentUser, tweet_comment.getSenderID());
                    Block_UnblockListenerImplementation implementation = new Block_UnblockListenerImplementation();
                    implementation.listenUnblock(event);
                    update(tweetComponentView);
                } else if(command == COMMANDS.DISLIKE){
                    Dislike_LikeEvent event = new Dislike_LikeEvent(currentUser, tweet_comment.getID());
                    Dislike_LikeListenerImplementation implementation = new Dislike_LikeListenerImplementation();
                    implementation.listenDislike(event);
                    update(tweetComponentView);
                } else if(command == COMMANDS.LIKE){
                    Dislike_LikeEvent event = new Dislike_LikeEvent(currentUser, tweet_comment.getID());
                    Dislike_LikeListenerImplementation implementation = new Dislike_LikeListenerImplementation();
                    implementation.listenLike(event);
                    update(tweetComponentView);
                } else if(command == COMMANDS.MUTE){
                    Mute_UnmuteEvent event = new Mute_UnmuteEvent(currentUser, tweet_comment.getSenderID());
                    Mute_UnMuteListenerImplementation implementation = new Mute_UnMuteListenerImplementation();
                    implementation.listenMuter(event);
                    update(tweetComponentView);
                } else if(command == COMMANDS.UNMUTE){
                    Mute_UnmuteEvent event = new Mute_UnmuteEvent(currentUser, tweet_comment.getSenderID());
                    Mute_UnMuteListenerImplementation implementation = new Mute_UnMuteListenerImplementation();
                    implementation.listenUnMuter(event);
                    update(tweetComponentView);
                } else if(command == COMMANDS.REPORT){
                    ReportListenerImplementation implementation = new ReportListenerImplementation();
                    ReportEvent event = new ReportEvent(tweet_comment);
                    implementation.listen(event);
                    update(tweetComponentView);
                } else if(command == COMMANDS.SAVE){
                    SaveTweetListenerImplementation implementation = new SaveTweetListenerImplementation();
                    SaveTweetEvent event = new SaveTweetEvent(currentUser, tweet_comment.getID());
                    implementation.listenSave(event);
                    update(tweetComponentView);
                } else if(command == COMMANDS.UNSAVE){
                    SaveTweetListenerImplementation implementation = new SaveTweetListenerImplementation();
                    SaveTweetEvent event = new SaveTweetEvent(currentUser, tweet_comment.getID());
                    implementation.listenUnsave(event);
                    update(tweetComponentView);
                } else if(command == COMMANDS.FORWARD){
                    Forward forward;
                    if (tweet_comment.getImageId() != null) {
                        forward = new Forward(currentUser, tweet_comment.getText(), tweet_comment.getImageId());
                    } else {
                        forward = new Forward(currentUser, tweet_comment.getText(), -2);
                    }
                    forward.show();
                    update(tweetComponentView);
                } else if(command == COMMANDS.RESEND){
                    if (currentUser.getId() != tweet_comment.getSenderID()) {
                        AddTweetListenerImplementation implementation = new AddTweetListenerImplementation();
                        TweetEvent event = null;
                        if (tweet_comment.getImageId() == null) {
                            event = new TweetEvent(Config.getConfig("tweet").getProperty(String.class, "resend") + " " + tweet_comment.getText(), currentUser.getId(), -2);
                        } else {
                            event = new TweetEvent(Config.getConfig("tweet").getProperty(String.class, "resend") + " " + tweet_comment.getText(), currentUser.getId(), tweet_comment.getImageId());
                        }
                        implementation.listen(event);
                        update(tweetComponentView);
                    }
                } else if (command == COMMANDS.SHOWCOMMENTS) {
                    CommentsPage page = new CommentsPage(tweet_comment);
                    page.create();
                }
                update(tweetComponentView);
            }
        });

    }

    private <T extends TweetComponentView> void update(T t){

        t.getLikesLabel().setText(Integer.toString(tweet_comment.getLikes()));
        t.getCommentsLabel().setText(Integer.toString(tweet_comment.getCommentsId().size()));
        if(currentUser.getLikedTweetsId().contains(new Integer(tweet_comment.getID()))){
            t.getLikeBtn().setVisible(false);
            t.getLikedBtn().setVisible(true);
        } else {
            t.getLikeBtn().setVisible(true);
            t.getLikedBtn().setVisible(false);
        }
        if(currentUser.getMutedId().contains(tweet_comment.getSenderID())){
            t.getMuteBtn().setVisible(false);
            t.getMutedBtn().setVisible(true);
        } else {
            t.getMuteBtn().setVisible(true);
            t.getMutedBtn().setVisible(false);
        }
        if(currentUser.getBlackListId().contains(tweet_comment.getSenderID())){
            t.getBlockBtn().setVisible(false);
            t.getBlockedBtn().setVisible(true);
        } else {
            t.getBlockBtn().setVisible(true);
            t.getBlockedBtn().setVisible(false);
        }
        if (currentUser.getSavedTweetsId().contains(tweet_comment.getID())) {
            t.getSaveBtn().setVisible(false);
            t.getUnsaveBtn().setVisible(true);
        } else {
            t.getSaveBtn().setVisible(true);
            t.getUnsaveBtn().setVisible(false);
        }
        Tweet_CommentController controller = new Tweet_CommentController();
        User sender = controller.getSender(tweet_comment);
        if (sender.getProfileImageId() != null) {
            ImageController controller1 = new ImageController();
            t.getCircle().setFill(new ImagePattern(controller1.getImage(sender.getProfileImageId())));
        }

    }

    private void updateImage(TweetWithImageComponentView view) {
        if (tweet_comment.getImageId() != null) {
            ImageController controller = new ImageController();
            view.getTweetImage().setImage(controller.getImage(tweet_comment.getImageId()));
        }
    }
}