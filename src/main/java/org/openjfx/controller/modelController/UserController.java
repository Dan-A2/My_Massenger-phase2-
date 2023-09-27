package org.openjfx.controller.modelController;

import org.openjfx.controller.Config;
import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.LinkedList;

public class UserController {

    static private final Logger logger = (Logger) LogManager.getLogger(UserController.class);

    public LinkedList<User> getFriends(User user) {
        LinkedList<User> friends = new LinkedList<>();
        for (Integer id : user.getFriendsId()) {
            for (User user1 : User.getActiveUsers()) {
                if(user1.getId() == id){
                    friends.add(user1);
                }
            }
        }
        return friends;
    }

    public LinkedList<User> getFollowers(User user) {
        LinkedList<User> followers = new LinkedList<>();
        for (Integer id : user.getFollowersId()) {
            for (User user1 : User.getActiveUsers()) {
                if(user1.getId() == id){
                    followers.add(user1);
                }
            }
        }
        return followers;
    }

    public LinkedList<User> getFollowings(User user) {
        LinkedList<User> followings = new LinkedList<>();
        for (Integer id : user.getFollowingsId()) {
            for (User user1 : User.getActiveUsers()) {
                if(user1.getId() == id){
                    followings.add(user1);
                }
            }
        }
        return followings;
    }

    public LinkedList<User> getBlackList(User user) {
        LinkedList<User> blackList = new LinkedList<>();
        for (Integer id : user.getBlackListId()) {
            for (User user1 : User.getActiveUsers()) {
                if(user1.getId() == id){
                    blackList.add(user1);
                }
            }
        }
        return blackList;
    }

    public LinkedList<User> getMuted(User user) {
        LinkedList<User> muted = new LinkedList<>();
        for (Integer id : user.getMutedId()) {
            for (User user1 : User.getActiveUsers()) {
                if(user1.getId() == id){
                    muted.add(user1);
                }
            }
        }
        return muted;
    }

    public LinkedList<Chat> getUnseenChats(User user) {
        LinkedList<Chat> unseenChats = new LinkedList<>();
        for (Integer id : user.getUnseenChatsId()) {
            for (Chat chat : Chat.getAllChats()) {
                if(chat.getID() == id){
                    unseenChats.add(chat);
                }
            }
        }
        return unseenChats;
    }

    public LinkedList<Tweet_Comment> getSavedTweets(User user) {
        LinkedList<Tweet_Comment> savedTweets = new LinkedList<>();
        for (Integer id : user.getSavedTweetsId()) {
            for (Tweet_Comment tweet : Tweet_Comment.getAll()) {
                if(tweet.getID() == id){
                    savedTweets.add(tweet);
                }
            }
        }
        return savedTweets;
    }

    public LinkedList<Tweet_Comment> getMyTweets(User user) {
        LinkedList<Tweet_Comment> myTweets = new LinkedList<>();
        for (Integer id : user.getMyTweetsId()) {
            for (Tweet_Comment tweet : Tweet_Comment.getAll()) {
                if(id.equals(tweet.getID())){
                    myTweets.add(tweet);
                }
            }
        }
        return myTweets;
    }

    public LinkedList<User> getRequesters(User user) {
        LinkedList<User> requesters = new LinkedList<>();
        for (Integer id : user.getRequestersId()) {
            for (User user1 : User.getActiveUsers()) {
                if(user1.getId() == id){
                    requesters.add(user1);
                }
            }
        }
        return requesters;
    }

    public LinkedList<Tweet_Comment> getLiked(User user) {
        LinkedList<Tweet_Comment> Liked = new LinkedList<>();
        for (Integer id : user.getLikedTweetsId()) {
            for (Tweet_Comment tweet : Tweet_Comment.getAll()) {
                if(tweet.getID() == id){
                    Liked.add(tweet);
                }
            }
        }
        return Liked;
    }

    public LinkedList<Chat> getMyChats(User user) {
        LinkedList<Chat> myChats = new LinkedList<>();
        for (Integer id : user.getMyChatsId()) {
            for (Chat chat : Chat.getAllChats()) {
                if(chat.getID() == id){
                    myChats.add(chat);
                }
            }
        }
        return myChats;
    }

    public LinkedList<Massage> getSavedMassages(User user) {
        LinkedList<Massage> savedMassages = new LinkedList<>();
        for (Integer id : user.getSavedMassagesId()) {
            for (Massage massage : Massage.getAllMassages()) {
                if(massage.getID() == id){
                    savedMassages.add(massage);
                }
            }
        }
        return savedMassages;
    }

    public LinkedList<Massage> getSavedTextsOfMe(User user) {
        LinkedList<Massage> savedTextsOfMe = new LinkedList<>();
        for (Integer id : user.getSavedTextsOfMeId()) {
            for (Massage massage : Massage.getAllMassages()) {
                if(massage.getID() == id){
                    savedTextsOfMe.add(massage);
                }
            }
        }
        return savedTextsOfMe;
    }

    public User find(String username){
        for (User user : User.getActiveUsers()) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        for (User user : User.getInactiveUsers()) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public User find(int userId){
        for (User user : User.getActiveUsers()) {
            if(user.getId() == userId){
                return user;
            }
        }
        for (User user : User.getInactiveUsers()) {
            if(user.getId() == userId){
                return user;
            }
        }
        return null;
    }

    public void blockUser(User user1, int user2Id){
        if (user1.getId() != user2Id) {
            logger.info("User " + user1.getId() + " has blocked User " + find(user2Id).getUsername());
            user1.getBlackListId().add(user2Id);
        }
        SaveNLoad.saveUsers();
    }

    public void unblockUser(User user1, int user2Id){
        logger.info("User " + user1.getId() + " has unblocked User " + find(user2Id).getUsername());
        user1.getBlackListId().remove(new Integer(user2Id));
        SaveNLoad.saveUsers();
    }

    public void like(User user, int tweetId){
        Tweet_CommentController controller = new Tweet_CommentController();
        NotificationHandler handler = new NotificationHandler();
        Tweet_Comment currentTweet = controller.find(tweetId);
        User tweeter = controller.getSender(currentTweet);
        logger.info("User " + user.getUsername() + " has liked Tweet " + tweetId);
        handler.sendNotif(user.getUsername() + Config.getConfig("tweet").getProperty(String.class, "likeTweetNotif"), tweeter);
        user.getLikedTweetsId().add(tweetId);
        SaveNLoad.saveUsers();
    }

    public void dislike(User user, int tweetId){
        Tweet_CommentController controller = new Tweet_CommentController();
        NotificationHandler handler = new NotificationHandler();
        Tweet_Comment currentTweet = controller.find(tweetId);
        User tweeter = controller.getSender(currentTweet);
        logger.info("User " + user.getUsername() + " has disliked Tweet " + tweetId);
        handler.sendNotif(user.getUsername() + Config.getConfig("tweet").getProperty(String.class, "dislikeTweetNotif"), tweeter);
        user.getLikedTweetsId().remove(new Integer(tweetId));
        SaveNLoad.saveUsers();
    }

    public void mute(User muter, int user2Id){
        if (muter.getId() != user2Id) {
            muter.getMutedId().add(user2Id);
            logger.info("User " + muter.getUsername() + " has muted " + find(user2Id).getUsername());
        }
        SaveNLoad.saveUsers();
    }

    public void unMute(User muter, int user2Id){
        muter.getMutedId().remove(new Integer(user2Id));
        logger.info("User " + muter.getUsername() + " has unmuted " + find(user2Id).getUsername());
        SaveNLoad.saveUsers();
    }

    public void saveTweet(User saver, int tweetId){
        if(!saver.getSavedTweetsId().contains(tweetId)) {
            saver.getSavedTweetsId().add(tweetId);
            logger.info("User" + saver.getUsername() + " has saved Tweet " + tweetId);
            SaveNLoad.saveUsers();
        }
    }

    public void changeBirthday(User user, LocalDate birthday){
        user.setBirthday(birthday);
        logger.info("User " + user.getUsername() + " has changed his/her birthday");
        SaveNLoad.saveUsers();
    }

    public void changeFirstName(User user, String name){
        user.setFirstName(name);
        logger.info("User " + user.getUsername() + " has changed his/her firstname");
        SaveNLoad.saveUsers();
    }

    public void changeLastName(User user, String name){
        user.setLastName(name);
        logger.info("User " + user.getUsername() + " has changed his/her lastname");
        SaveNLoad.saveUsers();
    }

    public void changeUsername(User user, String username){
        logger.info("User " + user.getUsername() + " has changed his/her username to " + username);
        user.setUsername(username);
        SaveNLoad.saveUsers();
    }

    public void changePassword(User user, String password){
        user.setPassword(password);
        logger.info("User " + user.getUsername() + " has changed his/her password");
        SaveNLoad.saveUsers();
    }

    public void changeEmail(User user, String email){
        user.setEmail(email);
        logger.info("User " + user.getUsername() + " has changed his/her email");
        SaveNLoad.saveUsers();
    }

    public void changePhoneNumber(User user, String phonenumber){
        user.setPhoneNumber(phonenumber);
        logger.info("User " + user.getUsername() + " has changed his/her phonenumber");
        SaveNLoad.saveUsers();
    }

    public void changeBio(User user, String bio){
        user.setBio(bio);
        logger.info("User " + user.getUsername() + " has changed his/her bio");
        SaveNLoad.saveUsers();
    }

    public void follow(User user1, User user2){
        if (user1.getId() != user2.getId()) {
            NotificationHandler handler = new NotificationHandler();
            handler.sendNotif(user1.getUsername() + " " + Config.getConfig("watchUserPage").getProperty(String.class, "followNotification"), user2);
            user1.getFollowingsId().add(user2.getId());
            user2.getFollowersId().add(user1.getId());
            logger.info("User " + user1.getUsername() + " has followed User " + user2.getUsername());
            SaveNLoad.saveUsers();
        }
    }

    public void unfollow(User user1, User user2){
        user1.getFollowingsId().remove(new Integer(user2.getId()));
        user2.getFollowersId().remove(new Integer(user1.getId()));
        logger.info("User " + user1.getUsername() + " has followed User " + user2.getUsername());
        SaveNLoad.saveUsers();
    }

    public void unSaveTweet(User saver, int tweetId) {
        saver.getSavedTweetsId().remove(new Integer(tweetId));
        logger.info("User" + saver.getUsername() + " has unsaved Tweet " + tweetId);
        SaveNLoad.saveUsers();
    }

    public void createSorting(User user, String sortingName, LinkedList<Integer> id){
        user.getMySortings().put(sortingName, id);
        logger.info("User " + user.getUsername() + " has created a sorting named " + sortingName);
        SaveNLoad.saveUsers();
    }

    public void removeSorting(User user, String sortingName, LinkedList<Integer> id){
        user.getMySortings().remove(sortingName, id);
        logger.info("User " + user.getUsername() + " has removed a sorting named " + sortingName);
        SaveNLoad.saveUsers();
    }

    public void deleteUser(User user) {

        MassageController controller = new MassageController();
        Tweet_CommentController controllerTweet = new Tweet_CommentController();
        for (User user1 : User.getActiveUsers()) {
            user1.getFriendsId().remove(new Integer(user.getId()));
            user1.getFollowingsId().remove(new Integer(user.getId()));
            user1.getFollowersId().remove(new Integer(user.getId()));
            for (int i = 0; i < getMyChats(user1).size(); i++) {
                Chat tmp = getMyChats(user1).get(i);
                if (tmp.getUser1() != null && tmp.getUser2() != null) {
                    if (tmp.getUser1().getUsername().equals(user.getUsername()) || tmp.getUser2().getUsername().equals(user.getUsername())) {
                        user1.getMyChatsId().remove(new Integer(tmp.getID()));
                        i--;
                    }
                }
            }
            for (int i = 0; i < getSavedMassages(user1).size(); i++) {
                if (controller.getSender(getSavedMassages(user1).get(i)) != null) {
                    if (controller.getSender(getSavedMassages(user1).get(i)).getUsername().equals(user.getUsername())) {
                        user1.getSavedMassagesId().remove(i);
                        i--;
                    }
                }
            }
            for (int i = 0; i < getSavedTweets(user1).size(); i++) {
                if (controllerTweet.getSender(getSavedTweets(user1).get(i)).getUsername().equals(user.getUsername())) {
                    user1.getSavedTweetsId().remove(i);
                    i--;
                }
            }
            for (String key : user1.getMySortings().keySet()) {
                for (int i = 0; i < user1.getMySortings().get(key).size(); i++) {
                    int userid = user1.getMySortings().get(key).get(i);
                    if(userid == user.getId()){
                        user1.getMySortings().get(key).remove(i);
                        i--;
                    }
                }
            }
        }
        for (User user1 : User.getInactiveUsers()) {
            user1.getFriendsId().remove(new Integer(user.getId()));
            user1.getFollowingsId().remove(new Integer(user.getId()));
            user1.getFollowersId().remove(new Integer(user.getId()));
            for (int i = 0; i < getMyChats(user1).size(); i++) {
                Chat tmp = getMyChats(user1).get(i);
                if (tmp.getUser1() != null && tmp.getUser2() != null) {
                    if (tmp.getUser1().getUsername().equals(user.getUsername()) || tmp.getUser2().getUsername().equals(user.getUsername())) {
                        user1.getMyChatsId().remove(new Integer(tmp.getID()));
                        i--;
                    }
                }
            }
            for (int i = 0; i < getSavedMassages(user1).size(); i++) {
                if (controller.getSender(getSavedMassages(user1).get(i)) != null) {
                    if (controller.getSender(getSavedMassages(user1).get(i)).getUsername().equals(user.getUsername())) {
                        user1.getSavedMassagesId().remove(i);
                        i--;
                    }
                }
            }
            for (int i = 0; i < getSavedTweets(user1).size(); i++) {
                if (controllerTweet.getSender(getSavedTweets(user1).get(i)).getUsername().equals(user.getUsername())) {
                    user1.getSavedTweetsId().remove(i);
                    i--;
                }
            }
            for (String key : user1.getMySortings().keySet()) {
                for (int i = 0; i < user1.getMySortings().get(key).size(); i++) {
                    int userid = user1.getMySortings().get(key).get(i);
                    if(userid == user.getId()){
                        user1.getMySortings().get(key).remove(i);
                        i--;
                    }
                }
            }
        }
//        for (int i = 0; i < Tweet_Comment.getAll().size(); i++) {
//            if (controllerTweet.getSender(Tweet_Comment.getAll().get(i)).getUsername().equals(user.getUsername())) {
//                Tweet_Comment.getAll().remove(new Integer(i));
//                i--;
//            }
//            for (int j = 0; j < Tweet_Comment.getAll().get(i).getCommentsId().size(); j++) {
//                int id = Tweet_Comment.getAll().get(i).getCommentsId().get(j);
//                if (controllerTweet.find(id).getSenderID() == user.getId()) {
//                    Tweet_Comment.getAll().get(i).getCommentsId().remove(new Integer(j));
//                }
//                j--;
//            }
//        }
        for (int i = 0; i < Tweet_Comment.getAll().size(); i++) {
            if (Tweet_Comment.getAll().get(i).getSenderID() == user.getId()) {
                Tweet_Comment tweet = Tweet_Comment.getAll().get(i);
                controllerTweet.deleteTweet(tweet);
                i--;
            }
        }
        for (int i = 0; i < Chat.getAllChats().size(); i++) {
            if (Chat.getAllChats().get(i).getUser1().getId() == user.getId() || Chat.getAllChats().get(i).getUser2().getId() == user.getId()) {
                if (Chat.getAllChats().get(i).getUser1().getId() == user.getId()) {
                    Chat.getAllChats().get(i).getUser1().getMyChatsId().remove(new Integer(Chat.getAllChats().get(i).getID()));
                } else {
                    Chat.getAllChats().get(i).getUser2().getMyChatsId().remove(new Integer(Chat.getAllChats().get(i).getID()));
                }
                Chat.getAllChats().remove(i);
                i--;
            }
        }
        for (int i = 0; i < GroupChat.getAllGroups().size(); i++) {
            GroupChat.getAllGroups().get(i).getUsersId().remove(new Integer(user.getId()));
        }
        logger.info("User " + user.getUsername() + " is deleted");
        User.getActiveUsers().remove(user);
        User.getInactiveUsers().remove(user);
        SaveNLoad.save();

    }

    public void clearNotifications(User user) {
        user.getMyNotifs().clear();
        SaveNLoad.saveUsers();
    }

    public void addUserToGroup(int userId, GroupChat groupChat) {
        User user = find(userId);
        NotificationHandler handler = new NotificationHandler();
        handler.sendNotif(Config.getConfig("chat").getProperty(String.class,"addedToGroupMassage") + groupChat.getGroupName(), user);
        user.getGroupsId().add(groupChat.getId());
        logger.info("User " + user.getUsername() + " is added to the Group " + groupChat.getId());
        user.getUnseenMassagesGroups().put(groupChat.getId(), 0);
        SaveNLoad.saveUsers();
    }

    public void saveMassage(User user, Massage massage) {
        if (!user.getSavedMassagesId().contains(massage.getID())) {
            logger.info("User " + user.getUsername() + " has saved Massage " + massage.getID());
            user.getSavedMassagesId().add(massage.getID());
        }
    }

    public void unsaveMassage(User user, Massage massage){
        user.getSavedMassagesId().remove(new Integer(massage.getID()));
        user.getSavedTextsOfMeId().remove(new Integer(massage.getID()));
        System.out.println(massage.getID());
        System.out.println(user.getSavedMassagesId());
        logger.info("User " + user.getUsername() + " has unsaved Massage " + massage.getID());
        SaveNLoad.saveUsers();
    }

    public void addFollowRequest(User user, User requester) {
        NotificationHandler handler = new NotificationHandler();
        user.getRequestersId().add(requester.getId());
        logger.info("User " + requester.getUsername() + " has requested to follow " + user.getUsername());
        handler.sendNotif(Config.getConfig("mainmenu").getProperty(String.class, "addFollowRequest"), user);
        SaveNLoad.saveUsers();
    }

    public void removeFollowRequest(User user, User requester) {
        user.getRequestersId().remove(new Integer(requester.getId()));
        logger.info("User " + requester.getUsername() + " got back the request to follow " + user.getUsername());
        SaveNLoad.saveUsers();
    }

    public void setLastSeen(User user) {
        user.setLastSeen();
        SaveNLoad.saveUsers();
    }

    public void changeSetting(User user, boolean accountPublicity, String whoCanSeeLastSeen, boolean accountActivity) {
        user.setAccountPublic(accountPublicity);
        user.setWhoCanSeeLastSeen(whoCanSeeLastSeen);
        user.setActive(accountActivity);
        if (accountActivity) {
            if (!User.getActiveUsers().contains(user)) {
                User.getActiveUsers().add(user);
            }
            User.getInactiveUsers().remove(user);
        } else {
            if (!User.getInactiveUsers().contains(user)) {
                User.getInactiveUsers().add(user);
            }
            User.getActiveUsers().remove(user);
        }
        logger.info("settings changed successfully for User " + user.getUsername());
        SaveNLoad.saveUsers();
    }

    public void saveNote(User user, String text) {
        Massage massage = new Massage(text, user.getId());
        user.getSavedTextsOfMeId().add(massage.getID());
        SaveNLoad.saveUsers();
    }
}