package org.openjfx.listeners.tweetNComment;

import org.openjfx.event.Dislike_LikeEvent;

public interface LikeDislikeListener {

    void listenLike(Dislike_LikeEvent event);
    void listenDislike(Dislike_LikeEvent event);

}
