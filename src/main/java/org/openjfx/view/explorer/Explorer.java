package org.openjfx.view.explorer;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.Tweet_CommentController;
import org.openjfx.controller.modelController.UserController;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.Tweet_Comment;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.explorer.search.Search;
import org.openjfx.view.tweet.TweetPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class Explorer {

    static private final Logger logger = (Logger) LogManager.getLogger(Explorer.class);
    private User user;
    private AnchorPane pane;

    public Explorer(User user) {
        this.user = user;
    }

    public void show(){

        Config config = Config.getConfig("explorer");
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class, "explorerAddress")));
        try {
            pane = loader.load();
            ExplorerView view = loader.getController();
            view.setExplorerListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if (command == COMMANDS.RAND) {
                        TweetPage tweetPage = new TweetPage();
                        LinkedList<Tweet_Comment> tweetsToBeShown = new LinkedList<>();
                        for (User user1: User.getActiveUsers()) {
                            UserController controller = new UserController();
                            if(!user.getFollowingsId().contains(user1.getId()) && user1.isAccountPublic() && user1.isActive() && !user1.getUsername().equals(user.getUsername())){ // else, it is in timeline
                                for (Tweet_Comment tweet: controller.getMyTweets(user1)) {
                                    tweetsToBeShown.add(tweet);
                                }
                            }
                        }
                        Tweet_CommentController controller = new Tweet_CommentController();
                        controller.sortByLike(tweetsToBeShown);
                        tweetPage.showInGeneral(tweetsToBeShown);
                    } else if (command == COMMANDS.SEARCH) {
                        Search search = new Search();
                        search.show();
                    }
                }
            });
            logger.info(config.getProperty(String.class,"explorerInfo"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"explorerWarn"));
            e.printStackTrace();
        }

    }

    public AnchorPane getPane() {
        return pane;
    }
}