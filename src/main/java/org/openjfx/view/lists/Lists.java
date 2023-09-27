package org.openjfx.view.lists;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.ChatController;
import org.openjfx.controller.modelController.GroupController;
import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.RemoveNotificationEvent;
import org.openjfx.listeners.CommandListener;
import org.openjfx.listeners.users.RemoveNotifListenerImplementation;
import org.openjfx.models.GroupChat;
import org.openjfx.models.Notification;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.lists.chat.ChatForList;
import org.openjfx.view.lists.chat.GroupForList;
import org.openjfx.view.lists.sorting.SortingForList;
import org.openjfx.view.lists.sorting.SortingForListForwardSend;
import org.openjfx.view.lists.user.forward.UserForListForward;
import org.openjfx.view.lists.user.goToPage.UserForListGoToPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.view.lists.user.requester.UserForListRequest;
import org.openjfx.view.yourAccount.NotificationFX;

import java.util.ArrayList;
import java.util.LinkedList;

public class Lists {

    static private final Logger logger = (Logger) LogManager.getLogger(Lists.class);
    private Config config = Config.getConfig("lists");
    private FXMLLoader loader1 = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"listsAddress")));
    private String massageToBeForwarded;
    private int imageId;

    public void show(User showTo, String type){
        try {
            Parent root = loader1.load();
            Scene scene = new Scene(root);
            ListsView currentView = (ListsView) loader1.getController();
            currentView.getTypeLabel().setText(type);
            currentView.getvBox().setAlignment(Pos.CENTER);
            setListener(currentView);
            UserController controller = new UserController();
            UserForListGoToPage userForListGoToPage = new UserForListGoToPage();
            if (config.getProperty(String.class,"blocked").equals(type)) {
                for (int i = 0; i < showTo.getBlackListId().size(); i++) {
                    String showFrom = controller.getBlackList(showTo).get(i).getUsername();
                    userForListGoToPage.createUser(showTo.getUsername(), showFrom);
                    currentView.getvBox().getChildren().add(userForListGoToPage.getBox());
                }
            } else if (config.getProperty(String.class,"followers").equals(type)) {
                for (int i = 0; i < showTo.getFollowersId().size(); i++) {
                    String showFrom = controller.getFollowers(showTo).get(i).getUsername();
                    userForListGoToPage.createUser(showTo.getUsername(), showFrom);
                    currentView.getvBox().getChildren().add(userForListGoToPage.getBox());
                }
            } else if (config.getProperty(String.class,"followings").equals(type)) {
                for (int i = 0; i < showTo.getFollowingsId().size(); i++) {
                    String showFrom = controller.getFollowings(showTo).get(i).getUsername();
                    userForListGoToPage.createUser(showTo.getUsername(), showFrom);
                    currentView.getvBox().getChildren().add(userForListGoToPage.getBox());
                }
            } else if (config.getProperty(String.class,"forward").equals(type)) {
                for (int i = 0; i < showTo.getFollowersId().size(); i++) {
                    UserForListForward userForListForward = new UserForListForward(showTo, controller.getFollowers(showTo).get(i), massageToBeForwarded, imageId);
                    currentView.getvBox().getChildren().add(userForListForward.gethBox());
                }
                for (int i = 0; i < showTo.getFollowingsId().size(); i++) {
                    UserForListForward userForListForward = new UserForListForward(showTo, controller.getFollowers(showTo).get(i), massageToBeForwarded, imageId);
                    currentView.getvBox().getChildren().add(userForListForward.gethBox());
                }
            } else if (config.getProperty(String.class,"sorting").equals(type)) {
                for (int i = 0; i < showTo.getMySortings().keySet().size(); i++) {
                    ArrayList<String> sortingList = new ArrayList<>(showTo.getMySortings().keySet());
                    SortingForList sortingForList = new SortingForList(showTo, sortingList.get(i));
                    currentView.getvBox().getChildren().add(sortingForList.gethBox());
                }
            } else if (config.getProperty(String.class,"forwardSorting").equals(type)) {
                for (int i = 0; i < showTo.getMySortings().keySet().size(); i++) {
                    ArrayList<String> sortingList = new ArrayList<>(showTo.getMySortings().keySet());
                    SortingForListForwardSend sortingForListForwardSend = new SortingForListForwardSend(showTo, sortingList.get(i), massageToBeForwarded, "forward", true, imageId);
                    currentView.getvBox().getChildren().add(sortingForListForwardSend.gethBox());
                }
            } else if (config.getProperty(String.class,"sendSorting").equals(type)) {
                for (int i = 0; i < showTo.getMySortings().keySet().size(); i++) {
                    ArrayList<String> sortingList = new ArrayList<>(showTo.getMySortings().keySet());
                    SortingForListForwardSend sortingForListForwardSend = new SortingForListForwardSend(showTo, sortingList.get(i), massageToBeForwarded, "send", false, imageId);
                    currentView.getvBox().getChildren().add(sortingForListForwardSend.gethBox());
                }
            } else if (config.getProperty(String.class, "chats").equals(type)) {
                ChatController controller1 = new ChatController();
                for (int i = 0; i < showTo.getMyChatsId().size(); i++) {
                    ChatForList chatForList;
                    if (controller1.findChat(showTo.getMyChatsId().get(i)).getUser1() == showTo) {
                        chatForList = new ChatForList(controller1.findChat(showTo.getMyChatsId().get(i)), controller1.findChat(showTo.getMyChatsId().get(i)).getUser1UnseenMassages());
                    } else {
                        chatForList = new ChatForList(controller1.findChat(showTo.getMyChatsId().get(i)), controller1.findChat(showTo.getMyChatsId().get(i)).getUser2UnseenMassages());
                    }
                    chatForList.show();
                    currentView.getvBox().getChildren().add(chatForList.getPane());
                }
            } else if (config.getProperty(String.class, "notif").equals(type)) {
                for (Notification notification : showTo.getMyNotifs()) {
                    NotificationFX notificationFX = new NotificationFX();
                    notificationFX.create(notification);
                    currentView.getvBox().getChildren().add(notificationFX.getPane());
                }
                RemoveNotificationEvent event = new RemoveNotificationEvent(showTo);
                RemoveNotifListenerImplementation implementation = new RemoveNotifListenerImplementation();
                implementation.listenRemove(event);
            } else if (config.getProperty(String.class, "requester").equals(type)) {
                for (int i = 0; i < showTo.getRequestersId().size(); i++) {
                    User requester = controller.find(showTo.getRequestersId().get(i));
                    UserForListRequest userForListRequest = new UserForListRequest();
                    userForListRequest.create(showTo, requester);
                    userForListRequest.setListener(new CommandListener() {
                        @Override
                        public void listenCommand(COMMANDS commands) {
                            currentView.getvBox().getChildren().remove(userForListRequest.getPane());
                        }
                    });
                    currentView.getvBox().getChildren().add(userForListRequest.getPane());
                }
            } else if (config.getProperty(String.class, "groupShow").equals(type)) {
                GroupController controller1 = new GroupController();
                for (int i = 0; i < showTo.getGroupsId().size(); i++) {
                    GroupChat groupChat = controller1.find(showTo.getGroupsId().get(i));
                    GroupForList groupForList = new GroupForList();
                    groupForList.create(showTo, groupChat);
                    currentView.getvBox().getChildren().add(groupForList.getPane());
                }
            }
            SceneManager.getSceneManager().push(scene);
            logger.info(config.getProperty(String.class,"listInfo"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"listWarn"));
            e.printStackTrace();
        }
    }

    public void showUsersOfSorting(User showTo, LinkedList<Integer> usersId, String sortingName){

        try {
            Parent root = loader1.load();
            Scene scene = new Scene(root);
            ListsView currentView = (ListsView) loader1.getController();
            currentView.getTypeLabel().setText(sortingName);
            setListener(currentView);
            UserController controller = new UserController();
            UserForListGoToPage userForListGoToPage = new UserForListGoToPage();
            for (int i = 0; i < usersId.size(); i++) {
                String showFrom = controller.find(usersId.get(i)).getUsername();
                userForListGoToPage.createUser(showTo.getUsername(), showFrom);
                currentView.getvBox().getChildren().add(userForListGoToPage.getBox());
            }
            SceneManager.getSceneManager().push(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setMassageToBeForwarded(String massageToBeForwarded) {
        this.massageToBeForwarded = massageToBeForwarded;
    }

    private void setListener(ListsView view){
        view.setListListener(new CommandListener() {
            @Override
            public void listenCommand(COMMANDS command) {
                if (command == COMMANDS.BACK) {
                    SceneManager.getSceneManager().getBack();
                } else if (command == COMMANDS.MAINMENU) {
                    SceneManager.getSceneManager().backToMain();
                }
            }
        });
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}