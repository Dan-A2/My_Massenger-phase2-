package org.openjfx.controller.mainMenuController;

import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.NotificationHandler;
import org.openjfx.controller.modelController.Tweet_CommentController;
import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.models.Tweet_Comment;
import org.openjfx.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommentController {

    static private final Logger logger = (Logger) LogManager.getLogger(CommentController.class);

    public void addComment(String txt, User sender, Tweet_Comment tweet) {

        if (!txt.equals("")) {
            Tweet_Comment comment = new Tweet_Comment(txt, sender.getId());
            NotificationHandler handler = new NotificationHandler();
            Tweet_CommentController controller = new Tweet_CommentController();
            handler.sendNotif("User " + sender.getUsername() + " " + Config.getConfig("mainmenu").getProperty(String.class, "addCommentNotif"), controller.getSender(tweet));
            logger.info(sender.getUsername() + " has added a comment to this tweet: " + tweet.getID());
            tweet.getCommentsId().add(comment.getID());
            SaveNLoad.saveTweet_Comments();
        }

    }

}
