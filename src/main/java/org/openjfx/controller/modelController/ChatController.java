package org.openjfx.controller.modelController;

import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.models.Chat;
import org.openjfx.models.Massage;
import org.openjfx.models.User;

import java.util.LinkedList;

public class ChatController {

    public Chat findChat(User u1, User u2){

        for (Chat chat : Chat.getAllChats()) {
            if((chat.getUser1().equals(u1) && chat.getUser2().equals(u2))||
                    (chat.getUser2().equals(u1) && chat.getUser1().equals(u2))){ // chat already exists
                return chat;
            }
        }
        return new Chat(u1.getId(), u2.getId());

    }

    public User findTheOtherUser(Chat chat, User user){
        if(chat.getUser1().getUsername().equals(user.getUsername())){
            return chat.getUser2();
        }
        return chat.getUser1();
    }

    public Chat findChat(int chatId) {
        for (Chat chat : Chat.getAllChats()) {
            if(chat.getID() == chatId){
                return chat;
            }
        }
        return null;
    }

    public LinkedList<Massage> getMassages(Chat chat) {
        MassageController controller = new MassageController();
        LinkedList<Massage> massages = new LinkedList<>();
        for (Integer id : chat.getMassagesId()) {
            if (controller.find(id) != null) {
                massages.add(controller.find(id));
            }
        }
        return massages;
    }

    public void resetMassages(Chat chat, User user) {
        if (chat.getUser1().getId() == user.getId()) {
            chat.setUser1UnseenMassages(0);
        } else {
            chat.setUser2UnseenMassages(0);
        }
        SaveNLoad.saveChats();
    }

}