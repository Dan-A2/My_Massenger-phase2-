package org.openjfx.controller.modelController;

import org.openjfx.controller.Config;
import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.models.GroupChat;
import org.openjfx.models.Massage;
import org.openjfx.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class GroupController {

    static private final Logger logger = (Logger) LogManager.getLogger(GroupController.class);

    private UserController controller = new UserController();

    public LinkedList<User> getUsers(GroupChat groupChat) {
        LinkedList<User> users = new LinkedList<>();
        for (int i = 0; i < groupChat.getUsersId().size(); i++) {
            int userId = groupChat.getUsersId().get(i);
            if (controller.find(userId) != null) {
                users.add(controller.find(userId));
            }
        }
        return users;
    }

    public void sendMassage(GroupChat groupChat, Massage massage, User sender) {
        groupChat.getMassages().add(massage.getID());
        addUnseenMassages(sender, groupChat);
        logger.info("User " + sender.getUsername() + " has sent a massage to the group " + groupChat.getId());
        SaveNLoad.saveGroups();
        SaveNLoad.saveMassages();
    }

    public void addUnseenMassages(User user, GroupChat groupChat) {
        NotificationHandler handler = new NotificationHandler();
        for (User user1 : getUsers(groupChat)) {
            if (user1.getId() != user.getId()) {
                handler.sendNotif(user.getUsername() + " " + Config.getConfig("chat").getProperty(String.class, "groupNewMassageNotif") + " " + groupChat.getGroupName(), user1);
                user1.getUnseenMassagesGroups().put(groupChat.getId(), user1.getUnseenMassagesGroups().get(groupChat.getId())+1);
            }
        }
    }

    public void leavingUser(User user, GroupChat groupChat) {
        Massage massage = new Massage(Config.getConfig("chat").getProperty(String.class, "leaveGroupMassage"), user.getId());
        sendMassage(groupChat, massage, user);
        logger.info("User " + user.getUsername() + " has left the group " + groupChat.getId());
        groupChat.getUsersId().remove(new Integer(user.getId()));
        user.getGroupsId().remove(new Integer(groupChat.getId()));
        user.getUnseenMassagesGroups().remove(groupChat.getId());
        SaveNLoad.saveGroups();
        SaveNLoad.saveUsers();
    }

    public GroupChat find(int id) {
        for (GroupChat groupChat : GroupChat.getAllGroups()) {
            if (groupChat.getId() == id) {
                return groupChat;
            }
        }
        return null;
    }

    public boolean checkGroupNameValid(User user, String name) {
        for (Integer id : user.getGroupsId()) {
            if (find(id).getGroupName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public void createGroup(User user, LinkedList<Integer> ids, String groupName) {
        GroupChat chat = new GroupChat(groupName);
        chat.setUsersId(ids);
        chat.getUsersId().add(user.getId());
        for (int id : chat.getUsersId()) {
            controller.addUserToGroup(id, chat);
        }
        logger.info("A new group with name: " + groupName + " has been created by " + user.getUsername());
        SaveNLoad.saveGroups();
    }

    public void resetMassages(User user, GroupChat chat) {
        user.getUnseenMassagesGroups().remove(chat.getId());
        user.getUnseenMassagesGroups().put(chat.getId(), 0);
        SaveNLoad.saveUsers();
    }

    public LinkedList<Massage> getMassages(GroupChat chat) {
        LinkedList<Massage> massages = new LinkedList<>();
        for (Integer id : chat.getMassages()) {
            for (Massage massage : Massage.getAllMassages()) {
                if(massage.getID() == id){
                    massages.add(massage);
                }
            }
        }
        return massages;
    }

    public void addMembers(GroupChat chat, LinkedList<Integer> id) {
        for (Integer id1 : id) {
            chat.getUsersId().add(id1);
            controller.addUserToGroup(id1, chat);
        }
        SaveNLoad.saveGroups();
    }
}