package org.openjfx.controller.modelController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.controller.Config;
import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.models.Chat;
import org.openjfx.models.Massage;
import org.openjfx.models.User;

public class SendingMassageController {

    private final Logger logger = (Logger) LogManager.getLogger(SendingMassageController.class);

    public void sendMassage1(User from, User to, String txt, boolean isForwarded, int imageId) {
        Massage massage = new Massage(txt, from.getId());
        massage.setForwarded(isForwarded);
        if (imageId > 0) {
            massage.setImageId(imageId);
        }
        ChatController controller = new ChatController();
        Chat chat = controller.findChat(to, from);
        chat.getMassagesId().add(massage.getID());
        if (chat.getUser1().equals(from)) {
            chat.setUser2UnseenMassages(chat.getUser2UnseenMassages() + 1);
        } else {
            chat.setUser1UnseenMassages(chat.getUser1UnseenMassages() + 1);
        }
        if (!to.getUnseenChatsId().contains(new Integer(chat.getID()))) {
            to.getUnseenChatsId().add(chat.getID());
        }
        if (!to.getMyChatsId().contains(new Integer(chat.getID()))) {
            to.getMyChatsId().add(chat.getID());
        }
        if (!from.getMyChatsId().contains(new Integer(chat.getID()))) {
            from.getMyChatsId().add(chat.getID());
        }
        if (!to.getMutedId().contains(from.getId())) {
            NotificationHandler handler = new NotificationHandler();
            Config config = Config.getConfig("massaging");
            handler.sendNotif(config.getProperty(String.class,"user") + " " + from.getUsername() + " " + config.getProperty(String.class, "sendMassageNotif"), to);
        }
        SaveNLoad.saveChats();
        SaveNLoad.saveUsers();
        logger.info(from.getUsername() + " has sent a massage to " + to.getUsername());
    }

    public void sendMassageSorting(User from, String sortingName, String txt, boolean isForwarded, int imageId){
        UserController controller = new UserController();
        for (Integer userId : from.getMySortings().get(sortingName)) {
            User to = controller.find(userId);
            sendMassage1(from, to, txt, isForwarded, imageId);
        }
    }

}