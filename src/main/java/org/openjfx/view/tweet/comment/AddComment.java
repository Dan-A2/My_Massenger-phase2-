package org.openjfx.view.tweet.comment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.CommentEvent;
import org.openjfx.listeners.tweetNComment.AddCommentListenerImplementation;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.Tweet_Comment;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddComment {

    static private final Logger logger = (Logger) LogManager.getLogger(AddComment.class);
    private User senDer;
    private FXMLLoader loader;

    public void show(User sender, Tweet_Comment tweet){
        senDer = sender;
        Config config = Config.getConfig("tweet");
        try {
            loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"addCommentAddress")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            AddCommentView thisView = loader.getController();
            thisView.getTweetLabel().setText(tweet.getText());
            UserController controller = new UserController();
            thisView.getUsernameLabel().setText(controller.find(tweet.getSenderID()).getUsername());
            thisView.setAddCommentListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if(command == COMMANDS.ADDCOMMENT){
                        createComment(thisView.getCommentArea().getText(), tweet);
                    } else if (command == COMMANDS.BACK) {
                        SceneManager.getSceneManager().getBack();
                    } else if (command == COMMANDS.MAINMENU) {
                        SceneManager.getSceneManager().backToMain();
                    }
                }
            });
            SceneManager.getSceneManager().push(scene);
            logger.info(config.getProperty(String.class,"addCommentInfo"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"addCommentWarn"));
            e.printStackTrace();
        }
    }

    private void createComment(String comment, Tweet_Comment mother){

        CommentEvent event = new CommentEvent(comment, senDer, mother);
        AddCommentListenerImplementation implementation = new AddCommentListenerImplementation();
        implementation.listen(event);
        SceneManager.getSceneManager().getBack();

    }

}
