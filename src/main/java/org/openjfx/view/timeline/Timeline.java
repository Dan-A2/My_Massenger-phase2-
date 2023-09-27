package org.openjfx.view.timeline;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.UserController;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.Tweet_Comment;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.tweet.TweetPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class Timeline {

    static private final Logger logger = (Logger) LogManager.getLogger(Timeline.class);
    private User user;
    private AnchorPane pane;

    public Timeline(User user) {
        this.user = user;
    }

    public void generate() {
        Config config = Config.getConfig("timeline");
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"address")));
        try {
            pane = loader.load();
            LinkedList<Tweet_Comment> followingTweets = new LinkedList<>();
            LinkedList<Tweet_Comment> likedTweets = new LinkedList<>();
            UserController controller = new UserController();
            for (User user1: controller.getFollowings(user)) {
                for (Tweet_Comment tweet: controller.getMyTweets(user1)) {
                    followingTweets.add(tweet);
                }
                for (Tweet_Comment tweet: controller.getLiked(user1)) {
                    likedTweets.add(tweet);
                }
            }
            TimelineView view = loader.getController();
            view.setTimelineListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if (command == COMMANDS.BACK) {
                        SceneManager.getSceneManager().getBack();
                    } else if (command == COMMANDS.MAINMENU) {
                        SceneManager.getSceneManager().backToMain();
                    } else if (command == COMMANDS.SHOWFOLLOWINGTWEET) {
                        TweetPage tweetPage = new TweetPage();
                        tweetPage.showInGeneral(followingTweets);
                    } else if (command == COMMANDS.SHOWLIKED) {
                        TweetPage tweetPage = new TweetPage();
                        tweetPage.showInGeneral(likedTweets);
                    }
                }
            });
            logger.info(config.getProperty(String.class, "info"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"warn"));
            e.printStackTrace();
        }
    }

    public AnchorPane getPane() {
        return pane;
    }
}