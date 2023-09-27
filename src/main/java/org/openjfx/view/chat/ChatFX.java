package org.openjfx.view.chat;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.*;
import org.openjfx.event.DeleteMassageEvent;
import org.openjfx.event.MassageEvent;
import org.openjfx.event.SaveMassageEvent;
import org.openjfx.event.SendMassageToGroupEvent;
import org.openjfx.listeners.massages.EditDeleteMassageListenerImplementation;
import org.openjfx.listeners.massages.MassageListenerImplementation;
import org.openjfx.listeners.CommandListener;
import org.openjfx.listeners.massages.SaveMassageListenerImplementation;
import org.openjfx.listeners.massages.SendMassageGroupListenerImplementation;
import org.openjfx.models.GroupChat;
import org.openjfx.models.Massage;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.chat.massage.withUserPic.MassageWithUserPic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.view.lists.ListSelectSortingGroup;
import org.openjfx.view.mainMenu.Mainmenu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChatFX {

    static private final Logger logger = (Logger) LogManager.getLogger(ChatFX.class);
    private Config chatConfig = Config.getConfig("chat");
    private FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(chatConfig.getProperty(String.class, "chatFXAddress")));
    private File data;

    public void generateChat(User user1, User user2){

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ChatView view = loader.getController();
            view.getNameLabel().setText(user2.getUsername());
            ChatController controller = new ChatController();
            updateChat(controller, view, user1, user2);
            view.setChatListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if (command == COMMANDS.SENDMASSAGE) {
                        sendMassage(user1, user2, view, controller);
                    } else if (command == COMMANDS.BACK) {
                        SceneManager.getSceneManager().getBack();
                    } else if (command == COMMANDS.MAINMENU) {
                        SceneManager.getSceneManager().backToMain();
                    } else if (command == COMMANDS.SELECTIMAGE) {
                        FileChooser fileChooser = new FileChooser();
                        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(Config.getConfig("yourAccount").getProperty("imageType"), "*.png");
                        fileChooser.getExtensionFilters().addAll(extFilterPNG);
                        try {
                            data = fileChooser.showOpenDialog(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.warn("an error occurred while trying to load image.");
                        }
                    }
                }
            });
            logger.info(chatConfig.getProperty(String.class,"chatInfo"));
            SceneManager.getSceneManager().push(scene);
        } catch (Exception e) {
            logger.warn(chatConfig.getProperty(String.class,"chatWarn"));
            e.printStackTrace();
        }

    }

    private void updateChat(ChatController controller, ChatView view, User user1, User user2){
        MassageController controller1 = new MassageController();
        view.getChatGrid().getChildren().clear();
        for (Massage massage : controller.getMassages(controller.findChat(user1, user2))) {
            if (controller1.getSender(massage).getId() == user1.getId()) {
                MassageWithUserPic massage1 = new MassageWithUserPic(user1, massage);
                massage1.create(Config.getConfig("chat").getProperty(String.class, "Deletable"));
                massage1.getView().getEditBtn().setVisible(!massage.isForwarded());
                massage1.getView().getDeleteBtn().setVisible(true);
                massage1.getView().setListener(new CommandListener() {
                    @Override
                    public void listenCommand(COMMANDS commands) {
                        if (commands == COMMANDS.EDIT) {
                            Edit edit = new Edit();
                            edit.show(massage);
                        } else if (commands == COMMANDS.DELETE) {
                            EditDeleteMassageListenerImplementation implementation = new EditDeleteMassageListenerImplementation();
                            DeleteMassageEvent event = new DeleteMassageEvent(massage, Mainmenu.getMenuView().getCurrentUser());
                            implementation.listenDeleteTotally(event);
                            updateChat(controller, view, user1, user2);
                        } else if (commands == COMMANDS.SAVE) {
                            SaveMassageEvent event = new SaveMassageEvent(user1, massage);
                            SaveMassageListenerImplementation implementation = new SaveMassageListenerImplementation();
                            implementation.listen(event);
                        }
                    }
                });
                view.getChatGrid().add(massage1.getPane(), 0, view.getChatGrid().getRowCount()+1);
                view.getChatGrid().setMargin(massage1.getPane(), new Insets(10));
            } else {
                MassageWithUserPic massage1 = new MassageWithUserPic(user2, massage);
                massage1.create(Config.getConfig("chat").getProperty(String.class, "noDelete"));
                massage1.getView().setListener(new CommandListener() {
                    @Override
                    public void listenCommand(COMMANDS commands) {
                        if (commands == COMMANDS.SAVE) {
                            SaveMassageEvent event = new SaveMassageEvent(user1, massage);
                            SaveMassageListenerImplementation implementation = new SaveMassageListenerImplementation();
                            implementation.listen(event);
                        }
                    }
                });
                view.getChatGrid().add(massage1.getPane(), 0, view.getChatGrid().getRowCount()+1);
                view.getChatGrid().setMargin(massage1.getPane(), new Insets(10));
            }
        }
    }

    public void generateGroup(User user, GroupChat groupChat){

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ChatView view = loader.getController();
            view.getNumberOfMembers().setVisible(true);
            view.getNumberOfMembers().setText(Integer.toString(groupChat.getUsersId().size()));
            view.getNameLabel().setText(groupChat.getGroupName());
            view.getAddUserBtn().setVisible(true);
            updateGroup(user, groupChat, view);
            view.setChatListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS commands) {
                    if (commands == COMMANDS.SENDMASSAGE) {
                        sendMassage(user, groupChat, view);
                    } else if (commands == COMMANDS.BACK) {
                        SceneManager.getSceneManager().getBack();
                    } else if (commands == COMMANDS.MAINMENU) {
                        SceneManager.getSceneManager().backToMain();
                    } else if (commands == COMMANDS.SELECTIMAGE) {
                        FileChooser fileChooser = new FileChooser();
                        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(Config.getConfig("yourAccount").getProperty("imageType"), "*.png");
                        fileChooser.getExtensionFilters().addAll(extFilterPNG);
                        try {
                            data = fileChooser.showOpenDialog(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.warn("an error occurred while trying to load image.");
                        }
                    } else if (commands == COMMANDS.ADDMEMBER) {
                        ListSelectSortingGroup listSelectSortingGroup = new ListSelectSortingGroup();
                        listSelectSortingGroup.generateForAddGroup(user, groupChat);
                    }
                }
            });
            SceneManager.getSceneManager().push(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateGroup(User user, GroupChat groupChat, ChatView view){

        GroupController controller = new GroupController();
        for (Massage massage : controller.getMassages(groupChat)) {
            if (massage.getSenderID() == user.getId()) {
                MassageWithUserPic massage1 = new MassageWithUserPic(user, massage);
                massage1.create(Config.getConfig("chat").getProperty(String.class, "Deletable"));
                massage1.getView().getEditBtn().setVisible(!massage.isForwarded());
                massage1.getView().getDeleteBtn().setVisible(true);
                massage1.getView().setListener(new CommandListener() {
                    @Override
                    public void listenCommand(COMMANDS commands) {
                        if (commands == COMMANDS.EDIT) {
                            Edit edit = new Edit();
                            edit.show(massage);
                        } else if (commands == COMMANDS.DELETE) {
                            EditDeleteMassageListenerImplementation implementation = new EditDeleteMassageListenerImplementation();
                            DeleteMassageEvent event = new DeleteMassageEvent(massage, Mainmenu.getMenuView().getCurrentUser());
                            implementation.listenDeleteTotally(event);
                            updateGroup(user, groupChat, view);
                        } else if (commands == COMMANDS.SAVE) {
                            SaveMassageEvent event = new SaveMassageEvent(user, massage);
                            SaveMassageListenerImplementation implementation = new SaveMassageListenerImplementation();
                            implementation.listen(event);
                        }
                    }
                });
                view.getChatGrid().add(massage1.getPane(), 0, view.getChatGrid().getRowCount()+1);
                view.getChatGrid().setMargin(massage1.getPane(), new Insets(10));
            } else {
                UserController userController = new UserController();
                MassageWithUserPic massage1 = new MassageWithUserPic(userController.find(massage.getSenderID()), massage);
                massage1.create(Config.getConfig("chat").getProperty(String.class, "noDelete"));
                massage1.getView().setListener(new CommandListener() {
                    @Override
                    public void listenCommand(COMMANDS commands) {
                        if (commands == COMMANDS.SAVE) {
                            SaveMassageEvent event = new SaveMassageEvent(user, massage);
                            SaveMassageListenerImplementation implementation = new SaveMassageListenerImplementation();
                            implementation.listen(event);
                        }
                    }
                });
                view.getChatGrid().add(massage1.getPane(), 0, view.getChatGrid().getRowCount()+1);
                view.getChatGrid().setMargin(massage1.getPane(), new Insets(10));
            }
        }

    }

    private void sendMassage(User user1, User user2, ChatView view, ChatController controller) {
        if (!view.getMassageArea().getText().equals("")) {
            MassageEvent event = null;
            if (data == null) {
                event = new MassageEvent(user1, user2, view.getMassageArea().getText(), -2);
            } else {
                ImageController controller1 = new ImageController();
                try {
                    BufferedImage bufferedImage = ImageIO.read(data);
                    int id = controller1.saveImage(SwingFXUtils.toFXImage(bufferedImage, null));
                    event = new MassageEvent(user1, user2, view.getMassageArea().getText(), id);
                    data = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            MassageListenerImplementation implementation = new MassageListenerImplementation();
            view.getMassageArea().clear();
            implementation.listen(event);
            updateChat(controller, view, user1, user2);
        }
    }

    private void sendMassage(User user, GroupChat chat, ChatView view) {
        if (!view.getMassageArea().getText().equals("")) {
            Massage massage = new Massage(view.getMassageArea().getText(), user.getId());
            if (data == null) {
                massage.setImageId(-2);
            } else {
                ImageController controller1 = new ImageController();
                try {
                    BufferedImage bufferedImage = ImageIO.read(data);
                    int id = controller1.saveImage(SwingFXUtils.toFXImage(bufferedImage, null));
                    massage.setImageId(id);
                    data = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            SendMassageToGroupEvent event = new SendMassageToGroupEvent(chat, massage, user);
            SendMassageGroupListenerImplementation implementation = new SendMassageGroupListenerImplementation();
            implementation.listen(event);
            view.getMassageArea().clear();
            updateGroup(user, chat, view);
        }
    }
}