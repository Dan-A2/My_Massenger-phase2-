package org.openjfx.listeners.tweetNComment;

import org.openjfx.controller.mainMenuController.TweetController;
import org.openjfx.event.TweetEvent;

public class AddTweetListenerImplementation implements AddTweetListener{

    private TweetController controller = new TweetController();

    @Override
    public void listen(TweetEvent event) {
        controller.postTweet(event.getText(), event.getSenderId(), event.getImageId());
    }
}