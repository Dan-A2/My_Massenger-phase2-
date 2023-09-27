package org.openjfx.controller.modelController;

import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.models.Chat;
import org.openjfx.models.GroupChat;
import org.openjfx.models.Massage;
import org.openjfx.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MassageController {

    static private final Logger logger = (Logger) LogManager.getLogger(MassageController.class);

    public void deleteMassage(Massage massage) {
        logger.info("massage with ID " + massage.getImageId() + " is deleted");
        for (User user : User.getActiveUsers()) {
            user.getSavedTextsOfMeId().remove(new Integer(massage.getID()));
            user.getSavedMassagesId().remove(new Integer(massage.getID()));
        }
        for (User user : User.getInactiveUsers()) {
            user.getSavedTextsOfMeId().remove(new Integer(massage.getID()));
            user.getSavedMassagesId().remove(new Integer(massage.getID()));
        }
        for (Chat chat : Chat.getAllChats()) {
            chat.getMassagesId().remove(new Integer(massage.getID()));
        }
        for (GroupChat groupChat : GroupChat.getAllGroups()) {
            groupChat.getMassages().remove(massage);
        }
        Massage.getAllMassages().remove(massage);
        SaveNLoad.saveMassages();
        SaveNLoad.saveUsers();
        SaveNLoad.saveChats();
    }

    public void editMassage(Massage massage, String text) {
        massage.setMassage(text);
        logger.info("massage " + massage.getID() + " is edited");
        SaveNLoad.saveMassages();
//        SaveNLoad.saveUsers();
    }

    public User getSender(Massage massage) {
        for (User user : User.getActiveUsers()) {
            if(user.getId() == massage.getSenderID()){
                return user;
            }
        }
        return null;
    }

    public Massage find(int id) {
        for (Massage massage:Massage.getAllMassages()) {
            if (massage.getID() == id) {
                return massage;
            }
        }
        return null;
    }

}