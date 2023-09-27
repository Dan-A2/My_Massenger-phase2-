package org.openjfx.listeners.tweetNComment;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.SaveTweetEvent;

public class SaveTweetListenerImplementation implements SaveTweetListener{

    private UserController controller = new UserController();

    @Override
    public void listenSave(SaveTweetEvent event) {
        controller.saveTweet(event.getSaver(), event.getTweetId());
    }

    @Override
    public void listenUnsave(SaveTweetEvent event) {
        controller.unSaveTweet(event.getSaver(), event.getTweetId());
    }

}
