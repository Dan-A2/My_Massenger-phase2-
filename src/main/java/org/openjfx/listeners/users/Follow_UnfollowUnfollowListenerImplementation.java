package org.openjfx.listeners.users;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.Follow_UnfollowEvent;

public class Follow_UnfollowUnfollowListenerImplementation implements FollowUnfollowListener{

    private UserController controller = new UserController();

    @Override
    public void listenFollow(Follow_UnfollowEvent event) {
        controller.follow(event.getUser1(), event.getUser2());
    }

    @Override
    public void listenUnfollow(Follow_UnfollowEvent event) {
        controller.unfollow(event.getUser1(), event.getUser2());
    }
}