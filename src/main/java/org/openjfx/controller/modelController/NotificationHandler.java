package org.openjfx.controller.modelController;

import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.models.Notification;
import org.openjfx.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NotificationHandler {

    static private final Logger logger = (Logger) LogManager.getLogger(NotificationHandler.class);

    public void sendNotif(String text, User receiver) {
        Notification notification = new Notification(text);
        receiver.getMyNotifs().add(notification);
        logger.info("a notification is sent to User " + receiver.getUsername());
        SaveNLoad.saveUsers();
    }

}