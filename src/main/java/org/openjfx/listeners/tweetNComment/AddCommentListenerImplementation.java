package org.openjfx.listeners.tweetNComment;

import org.openjfx.controller.mainMenuController.CommentController;
import org.openjfx.event.CommentEvent;

public class AddCommentListenerImplementation implements AddCommentListener{

    private CommentController controller = new CommentController();

    @Override
    public void listen(CommentEvent event) {
        controller.addComment(event.getTxt(), event.getSender(), event.getTweetComment());
    }
}
