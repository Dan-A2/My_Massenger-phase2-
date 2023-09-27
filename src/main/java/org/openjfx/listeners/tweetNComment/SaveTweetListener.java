package org.openjfx.listeners.tweetNComment;

import org.openjfx.event.SaveTweetEvent;

public interface SaveTweetListener {

    void listenSave(SaveTweetEvent event);
    void listenUnsave(SaveTweetEvent event);

}
