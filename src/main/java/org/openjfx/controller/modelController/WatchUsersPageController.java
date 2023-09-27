package org.openjfx.controller.modelController;

import org.openjfx.models.User;
import org.openjfx.view.watchUserPage.WatchUserPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class WatchUsersPageController {

    static private final Logger logger = (Logger) LogManager.getLogger(WatchUsersPageController.class);
    private User showTo;
    private User showFrom;

    public void goToPage(String showF, String showT) {
        UserController controller = new UserController();
        logger.info("User " + showT + " is going to " + showF + "'s page");
        showFrom = controller.find(showF);
        showTo = controller.find(showT);
        if(!showFrom.getBlackListId().contains(showTo.getId())){
            WatchUserPage n = new WatchUserPage(showFrom, showTo);
            n.show();
        } else {
            JOptionPane.showMessageDialog(null, "you cant enter this page");
        }
    }

}
