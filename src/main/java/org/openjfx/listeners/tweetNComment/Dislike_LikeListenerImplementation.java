package org.openjfx.listeners.tweetNComment;

import org.openjfx.controller.modelController.Tweet_CommentController;
import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.Dislike_LikeEvent;

public class Dislike_LikeListenerImplementation implements LikeDislikeListener {

    private Tweet_CommentController controller1 = new Tweet_CommentController();
    private UserController controller2 = new UserController();

    @Override
    public void listenDislike(Dislike_LikeEvent event) {
        controller1.dislike(event.getTweetId());
        controller2.dislike(event.getDespicable(), event.getTweetId());
    }

    @Override
    public void listenLike(Dislike_LikeEvent event) {
        controller1.like(event.getTweetId());
        controller2.like(event.getDespicable(), event.getTweetId());
    }

}