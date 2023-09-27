package org.openjfx.view.tweet.comment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.ImagePattern;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.ImageController;
import org.openjfx.controller.modelController.Tweet_CommentController;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.Tweet_Comment;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.tweet.TweetComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommentsPage {

    static private final Logger logger = (Logger) LogManager.getLogger(CommentsPage.class);
    private Tweet_Comment motherTweet;
    public CommentsPage(Tweet_Comment motherTweet) {
        this.motherTweet = motherTweet;
    }

    public void create(){

        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(Config.getConfig("tweet").getProperty(String.class,"commentAddress")));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            CommentsPageView view = loader.getController();
            view.getTweetLabel().setText(motherTweet.getText());
            Tweet_CommentController controller = new Tweet_CommentController();
            ImageController controller1 = new ImageController();
            view.getUsernameLabel().setText(controller.getSender(motherTweet).getUsername());
            if (controller.getSender(motherTweet).getProfileImageId() != null) {
                view.getCircle().setFill(new ImagePattern(controller1.getImage(controller.getSender(motherTweet).getProfileImageId())));
            }
            for (Tweet_Comment comment : controller.getComments(motherTweet)) {
                TweetComponent comment2 = new TweetComponent();
                comment2.generate(comment);
                view.getVBox().getChildren().add(comment2.getPane());
            }
            view.setListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if (command == COMMANDS.BACK) {
                        SceneManager.getSceneManager().getBack();
                    } else if (command == COMMANDS.MAINMENU) {
                        SceneManager.getSceneManager().backToMain();
                    }
                }
            });
            SceneManager.getSceneManager().push(scene);
            logger.info(Config.getConfig("tweet").getProperty(String.class,"commentInfo"));
        } catch (Exception e) {
            logger.warn(Config.getConfig("tweet").getProperty(String.class,"commentWarn"));
            e.printStackTrace();
        }

    }

}