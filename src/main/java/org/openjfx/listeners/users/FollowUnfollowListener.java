package org.openjfx.listeners.users;

import org.openjfx.event.Follow_UnfollowEvent;

public interface FollowUnfollowListener {

    void listenFollow(Follow_UnfollowEvent event);
    void listenUnfollow(Follow_UnfollowEvent event);

}