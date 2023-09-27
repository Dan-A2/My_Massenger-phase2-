package org.openjfx.controller.saveLoad;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.controller.Config;
import org.openjfx.models.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

public class SaveNLoad {

    static private final Logger logger = (Logger) LogManager.getLogger(SaveNLoad.class);
    static Gson gson = new Gson();

    private static Config config = Config.getConfig("saveNLoad");

    public static void saveUsers(){

        String path = config.getProperty(String.class,"userAddress");
        try {
            File file = new File(path);
            for (File file1 : file.listFiles()) {
                file1.delete();
            }
            File activeUsers = new File(path + config.getProperty(String.class,"active"));
            File inactiveUsers = new File(path + config.getProperty(String.class,"inactive"));
            activeUsers.createNewFile();
            inactiveUsers.createNewFile();
            PrintStream printStream1 = new PrintStream(new FileOutputStream(activeUsers, true));
            PrintStream printStream2 = new PrintStream(new FileOutputStream(inactiveUsers, true));
            // deleting repeated objects
//            for (int i = 0; i < User.getActiveUsers().size()-1; i++) {
//                for (int j = i+1; j < User.getActiveUsers().size(); j++) {
//                    if (User.getActiveUsers().get(i) == User.getActiveUsers().get(j)){
//                        User.getActiveUsers().remove(i);
//                        i = -1;
//                        break;
//                    }
//                }
//            }
//            for (int i = 0; i < User.getInactiveUsers().size()-1; i++) {
//                for (int j = i+1; j < User.getInactiveUsers().size(); j++) {
//                    if (User.getInactiveUsers().get(i) == User.getInactiveUsers().get(j)){
//                        User.getInactiveUsers().remove(i);
//                        i = -1;
//                        break;
//                    }
//                }
//            }
            for (User user : User.getActiveUsers()) {
                String json = gson.toJson(user);
                printStream1.println(json);
            }
            for (User user : User.getInactiveUsers()) {
                String json = gson.toJson(user);
                printStream2.println(json);
            }
            printStream1.flush();
            printStream2.flush();
            printStream1.close();
            printStream2.close();
            logger.info("users saved successfully");
        } catch (Exception e) {
            logger.fatal("an exception occurred while saving users");
            e.printStackTrace();
        }

    }

    public static void saveTweet_Comments(){

        String path = config.getProperty(String.class, "tweet_commentAddress");
        try {
            File file = new File(path);
            for (File file1 : file.listFiles()){
                file1.delete();
            }
            File tweets = new File(path + config.getProperty(String.class,"tweetComment"));
            tweets.createNewFile();
            PrintStream printStream1 = new PrintStream(new FileOutputStream(tweets, true));
//            for (int i = 0; i< Tweet_Comment.getAll().size()-1; i++){
//                for (int j = i+1; j < Tweet_Comment.getAll().size(); j++) {
//                    if(Tweet_Comment.getAll().get(i) == Tweet_Comment.getAll().get(j)){
//                        Tweet_Comment.getAll().remove(i);
//                        i = -1;
//                        break;
//                    }
//                }
//            }
            for (Tweet_Comment tweet: Tweet_Comment.getAll()) {
                String json = gson.toJson(tweet);
                printStream1.println(json);
            }
            printStream1.flush();
            printStream1.close();
            logger.info("tweets and comments saved successfully");
        } catch (Exception e){
            logger.fatal("an error occurred while saving tweets and comments");
            e.printStackTrace();
        }

    }

    public static void saveChats(){

        String path = config.getProperty(String.class, "chatsAddress");
        try {
            File file = new File(path);
            for (File file1 : file.listFiles()){
                file1.delete();
            }
            File chats = new File(path + config.getProperty(String.class,"chat"));
            chats.createNewFile();
            PrintStream printStream1 = new PrintStream(new FileOutputStream(chats, true));
//            for (int i = 0; i< Chat.getAllChats().size()-1; i++){
//                for (int j = i+1; j < Chat.getAllChats().size(); j++) {
//                    if(Chat.getAllChats().get(i) == Chat.getAllChats().get(j)){
//                        Chat.getAllChats().remove(i);
//                        i = -1;
//                        break;
//                    }
//                }
//            }
            for (Chat chat : Chat.getAllChats()) {
                String json = gson.toJson(chat);
                printStream1.println(json);
            }
            printStream1.flush();
            printStream1.close();
            logger.info("chats saved successfully");
            saveMassages();
        } catch (Exception e){
            e.printStackTrace();
            logger.fatal("an error occurred while saving chats");
        }

    }

    public static void saveMassages(){

        String path = config.getProperty(String.class, "massagesAddress");
        try {
            File file = new File(path);
            for (File file1 : file.listFiles()){
                file1.delete();
            }
            File massages = new File(path + config.getProperty(String.class, "massage"));
            massages.createNewFile();
            PrintStream printStream1 = new PrintStream(new FileOutputStream(massages, true));
//            for (int i = 0; i< Massage.getAllMassages().size()-1; i++){
//                for (int j = i+1; j < Massage.getAllMassages().size(); j++) {
//                    if(Massage.getAllMassages().get(i) == Massage.getAllMassages().get(j)){
//                        Massage.getAllMassages().remove(i);
//                        i = -1;
//                        break;
//                    }
//                }
//            }
            for (Massage massage : Massage.getAllMassages()) {
                String json = gson.toJson(massage);
                printStream1.println(json);
            }
            printStream1.flush();
            printStream1.close();
            logger.info("massages saved successfully");
        } catch (Exception e){
            logger.fatal("an error occurred while saving massages");
            e.printStackTrace();
        }

    }

    public static void saveGroups() {

        String path = config.getProperty(String.class, "groupsAddress");
        try {
            File file = new File(path);
            for (File file1 : file.listFiles()){
                file1.delete();
            }
            File groups = new File(path + config.getProperty(String.class, "group"));
            groups.createNewFile();
            PrintStream printStream1 = new PrintStream(new FileOutputStream(groups, true));
            for (GroupChat group : GroupChat.getAllGroups()) {
                String json = gson.toJson(group);
                printStream1.println(json);
            }
            printStream1.flush();
            printStream1.close();
            logger.info("groups saved successfully");
        } catch (Exception e) {
            logger.fatal("an error occurred while saving groups");
            e.printStackTrace();
        }

    }

    public static void saveImageId() {

        String path = config.getProperty(String.class, "imageLastId");
        try {
            File file = new File(path);
            for (File file1 : file.listFiles()){
                file1.delete();
            }
            File imageId = new File(path + config.getProperty(String.class, "imageId"));
            imageId.createNewFile();
            PrintStream printStream1 = new PrintStream(new FileOutputStream(imageId, true));
            printStream1.println(Image.getLastID());
            printStream1.flush();
            printStream1.close();
            logger.info("image ID saved successfully");
        } catch (Exception e) {
            logger.fatal("an error occurred while saving image ID");
            e.printStackTrace();
        }

    }

    public static void loadUsers(){

        String path1 = config.getProperty(String.class, "userAddress") + config.getProperty(String.class, "active");
        String path2 = config.getProperty(String.class, "userAddress") + config.getProperty(String.class, "inactive");
        try{
            int maxId = 0;
            File active = new File(path1);
            if(active.exists()){
                Scanner scanner = new Scanner(active);
                while (scanner.hasNext()){
                    String json = scanner.nextLine();
                    User user = gson.fromJson(json, User.class);
                    if(user.getId() > maxId){
                        maxId = user.getId();
                    }
                    User.getActiveUsers().add(user);
                }
                scanner.close();
            }
            File inactive = new File(path2);
            if(inactive.exists()){
                Scanner scanner = new Scanner(inactive);
                while (scanner.hasNext()){
                    String json = scanner.nextLine();
                    User user = gson.fromJson(json, User.class);
                    if(user.getId() > maxId){
                        maxId = user.getId();
                    }
                    User.getInactiveUsers().add(user);
                }
                scanner.close();
            }
            int id = maxId;
            User.setLastId(id);
            logger.info("users loaded successfully");
        }
        catch (Exception e){
            logger.fatal("an error occurred while loading users");
            e.printStackTrace();
        }

    }

    public static void loadTweet_Comments(){

        String path = config.getProperty(String.class, "tweet_commentAddress") + config.getProperty(String.class, "tweetComment");;
        File file = new File(path);
        int maxid = 0;
        try {
            if(file.exists()){
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    String json = scanner.nextLine();
                    Tweet_Comment tweet = gson.fromJson(json, Tweet_Comment.class);
                    Tweet_Comment.getAll().add(tweet);
                    if(tweet.getID() > maxid){
                        maxid = tweet.getID();
                    }
                }
                scanner.close();
            }
            Tweet_Comment.setLastId(maxid);
            logger.info("tweets loaded successfully");
        } catch (Exception e){
            logger.fatal("an error occurred while loading tweets");
            e.printStackTrace();
        }

    }

    public static void loadChats(){

        String path = config.getProperty(String.class, "chatsAddress") + config.getProperty(String.class, "chat");
        int maxid = 0;
        try {
            File file = new File(path);
            if(file.exists()){
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    String json = scanner.nextLine();
                    Chat chat = gson.fromJson(json, Chat.class);
                    Chat.getAllChats().add(chat);
                    if(chat.getID() > maxid){
                        maxid = chat.getID();
                    }
                }
                scanner.close();
            }
            Chat.setLastId(maxid);
            logger.info("chats loaded successfully");
        } catch (Exception e) {
            logger.fatal("an error occurred while loading chats");
            e.printStackTrace();
        }

    }

    public static void loadMassages(){

        String path = config.getProperty(String.class, "massagesAddress") + config.getProperty(String.class, "massage");
        int maxid = 0;
        try {
            File file = new File(path);
            if(file.exists()){
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    String json = scanner.nextLine();
                    Massage massage = gson.fromJson(json, Massage.class);
                    Massage.getAllMassages().add(massage);
                    if(massage.getID() > maxid){
                        maxid = massage.getID();
                    }
                }
                Massage.setLastId(maxid);
                scanner.close();
            }
            logger.info("massages loaded successfully");
        } catch (Exception e){
            logger.fatal("an error occurred while loading chats");
            e.printStackTrace();
        }

    }

    public static void loadGroups(){

        String path = config.getProperty(String.class, "groupsAddress") + config.getProperty(String.class, "group");
        int maxid = 0;
        try {
            File file = new File(path);
            if(file.exists()){
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    String json = scanner.nextLine();
                    GroupChat group = gson.fromJson(json, GroupChat.class);
                    GroupChat.getAllGroups().add(group);
                    if(group.getId() > maxid){
                        maxid = group.getId();
                    }
                }
                GroupChat.setLastID(maxid);
                scanner.close();
            }
            logger.info("massages loaded successfully");
        } catch (Exception e){
            logger.fatal("an error occurred while loading chats");
            e.printStackTrace();
        }

    }

    public static void loadImageId() {

        String path = config.getProperty(String.class, "imageLastId") + config.getProperty(String.class, "imageId");
        try {
            File file = new File(path);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                int lastId = Integer.parseInt(scanner.next());
                Image.setLastID(lastId);
                scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void save(){

        saveUsers();
        saveChats();
        saveTweet_Comments();
        saveMassages();
        saveGroups();
        saveImageId();

    }

    public static void load(){

        loadTweet_Comments();
        loadChats();
        loadUsers();
        loadMassages();
        loadGroups();
        loadImageId();

    }

}