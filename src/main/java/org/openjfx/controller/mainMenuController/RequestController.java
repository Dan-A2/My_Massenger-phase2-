package org.openjfx.controller.mainMenuController;

import org.openjfx.controller.Config;
import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.models.Notification;
import org.openjfx.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestController {

    static private final Logger logger = (Logger) LogManager.getLogger(RequestController.class);

    public void acceptRequest(User doer, User requester) {
        Config config = Config.getConfig("mainmenu");
        if (!doer.getFollowersId().contains(requester.getId())) {
            doer.getFollowersId().add(requester.getId());
            requester.getFollowingsId().add(doer.getId());
        }
        requester.getMyNotifs().add(new Notification(config.getProperty(String.class,"user") + " " + doer.getUsername() + " " + config.getProperty(String.class,"followRequestAccept")));
        logger.info("User " + doer.getUsername() + " has accepted the follow request of " + requester.getUsername());
        ignoreRequest(doer, requester);
        SaveNLoad.saveUsers();
    }

    public void ignoreRequest(User doer, User requester) {
        doer.getRequestersId().remove(new Integer(requester.getId()));
        logger.info("User " + doer.getUsername() + " has ignored the follow request of " + requester.getUsername());
        SaveNLoad.saveUsers();
    }

    public void denyRequest(User doer, User requester) {
        Config config = Config.getConfig("mainmenu");
        logger.info("User " + doer.getUsername() + " has denied the follow request of " + requester.getUsername());
        ignoreRequest(doer, requester);
        requester.getMyNotifs().add(new Notification(config.getProperty(String.class,"user") + " " + doer.getUsername() + " " + config.getProperty(String.class,"followRequestDeny")));
        SaveNLoad.saveUsers();
    }

}