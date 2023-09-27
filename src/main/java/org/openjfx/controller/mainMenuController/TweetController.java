package org.openjfx.controller.mainMenuController;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.models.Tweet_Comment;
import org.openjfx.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TweetController {

    static private final Logger logger = (Logger) LogManager.getLogger(TweetController.class);

    public void postTweet(String txt, int senderId, int imageId){
        if(!txt.equals("")){
            Tweet_Comment tweet = new Tweet_Comment(txt, senderId);
            if (imageId > 0) {
                tweet.setImageId(imageId);
                SaveNLoad.saveTweet_Comments();
            }
            UserController controller = new UserController();
            logger.info("User " + senderId + " has posted a tweet");
            User user = controller.find(senderId);
            user.getMyTweetsId().add(tweet.getID());
            SaveNLoad.saveUsers();
        }
    }

}