package org.openjfx.view.lists;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.GroupController;
import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.AddMembersToGroupEvent;
import org.openjfx.event.CreateGroupEvent;
import org.openjfx.event.CreateSortingEvent;
import org.openjfx.listeners.massages.AddMembersToGroupListenerImplementation;
import org.openjfx.listeners.massages.CreateGroupListenerImplementation;
import org.openjfx.listeners.sortings.Create_RemoveSortingLI;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.GroupChat;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.lists.user.select.UserForListSelect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.LinkedList;

public class ListSelectSortingGroup {

    static private final Logger logger = (Logger) LogManager.getLogger(ListSelectSortingGroup.class);
    private Config config = Config.getConfig("lists");
    private FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"listSortingAddress")));
    private User user;
    private LinkedList<Integer> id;

    public void generate(User user, boolean isSorting){
        this.user = user;
        id = new LinkedList<>();
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ListSelectSortingGroupView view = loader.getController();
            view.getvBox().setAlignment(Pos.CENTER);
            view.getTypeLabel().setText(config.getProperty(String.class,"listSelectTitle"));
            setListener(view, isSorting);
            UserController controller = new UserController();
            for (int i = 0; i < user.getFollowingsId().size(); i++) {
                User user1 = controller.find(user.getFollowingsId().get(i));
                UserForListSelect userForListSelect = new UserForListSelect();
                userForListSelect.create(user1);
                view.getvBox().getChildren().add(userForListSelect.getBox());
                userForListSelect.setSelectListener(new CommandListener() {
                    @Override
                    public void listenCommand(COMMANDS command) {
                        if (command == COMMANDS.SELECT) {
                            id.add(user1.getId());
                        } else if (command == COMMANDS.DESELECT){
                            id.remove(new Integer(user1.getId()));
                        }
                    }
                });
            }
            SceneManager.getSceneManager().push(scene);
            logger.info(config.getProperty(String.class,"listInfo"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"listWarn"));
            e.printStackTrace();
        }
    }

    private void setListener(ListSelectSortingGroupView view, boolean isSorting){
        view.setListener(new CommandListener() {
            @Override
            public void listenCommand(COMMANDS command) {
                if (command == COMMANDS.BACK) {
                    SceneManager.getSceneManager().getBack();
                } else if (command == COMMANDS.MAINMENU) {
                    SceneManager.getSceneManager().backToMain();
                } else if (command == COMMANDS.CREATE) {
                    if (isSorting) {
                        createSorting(view);
                    } else {
                        createGroup(view);
                    }
                }
            }
        });
    }

    private void createSorting(ListSelectSortingGroupView view) {
        if (id.size() <= 1) {
            JOptionPane.showMessageDialog(null, config.getProperty(String.class,"lowUsers"));
        } else if (view.getSortingNameField().getText().equals("")) {
            JOptionPane.showMessageDialog(null, config.getProperty(String.class,"empty"));
        } else if (user.getMySortings().keySet().contains(view.getSortingNameField().getText())) {
            JOptionPane.showMessageDialog(null, config.getProperty(String.class,"repeated"));
        } else {
            CreateSortingEvent event = new CreateSortingEvent(id, view.getSortingNameField().getText(), user);
            Create_RemoveSortingLI implementation = new Create_RemoveSortingLI();
            implementation.listenCreate(event);
        }
    }

    private void createGroup(ListSelectSortingGroupView view) {
        GroupController controller = new GroupController();
        if (id.size() <= 1) {
            JOptionPane.showMessageDialog(null, config.getProperty(String.class,"lowUsers"));
        } else if (view.getSortingNameField().getText().equals("")) {
            JOptionPane.showMessageDialog(null, config.getProperty(String.class,"empty"));
        } else if (!controller.checkGroupNameValid(user, view.getSortingNameField().getText())) {
            JOptionPane.showMessageDialog(null, config.getProperty(String.class,"repeated"));
        } else {
            CreateGroupEvent event = new CreateGroupEvent(user, id, view.getSortingNameField().getText());
            CreateGroupListenerImplementation implementation = new CreateGroupListenerImplementation();
            implementation.listen(event);
        }
    }

    public void generateForAddGroup(User adder, GroupChat chat) {
        LinkedList<Integer> newId = new LinkedList<>();
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ListSelectSortingGroupView view = loader.getController();
            view.getvBox().setAlignment(Pos.CENTER);
            view.getTypeLabel().setText(config.getProperty(String.class,"addMember"));
            view.getSortingNameField().setVisible(false);
            view.getCreateBtn().setText("add");
            view.setListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if (command == COMMANDS.BACK) {
                        SceneManager.getSceneManager().getBack();
                    } else if (command == COMMANDS.MAINMENU) {
                        SceneManager.getSceneManager().backToMain();
                    } else if (command == COMMANDS.CREATE) {
                        if (newId.size() > 0) {
                            addMembers(chat, newId);
                        } else {
                            JOptionPane.showMessageDialog(null,"no users to add");
                        }
                    }
                }
            });
            UserController controller = new UserController();
            for (int i = 0; i < adder.getFollowingsId().size(); i++) {
                if (!chat.getUsersId().contains(adder.getFollowingsId().get(i))) {
                    User user1 = controller.find(adder.getFollowingsId().get(i));
                    UserForListSelect userForListSelect = new UserForListSelect();
                    userForListSelect.create(user1);
                    view.getvBox().getChildren().add(userForListSelect.getBox());
                    userForListSelect.setSelectListener(new CommandListener() {
                        @Override
                        public void listenCommand(COMMANDS command) {
                            if (command == COMMANDS.SELECT) {
                                newId.add(user1.getId());
                            } else if (command == COMMANDS.DESELECT) {
                                newId.remove(new Integer(user1.getId()));
                            }
                        }
                    });
                }
            }
            SceneManager.getSceneManager().push(scene);
            logger.info(config.getProperty(String.class,"listInfo"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"listWarn"));
            e.printStackTrace();
        }
    }

    private void addMembers(GroupChat chat, LinkedList<Integer> id) {
        AddMembersToGroupEvent event = new AddMembersToGroupEvent(chat, id);
        AddMembersToGroupListenerImplementation implementation = new AddMembersToGroupListenerImplementation();
        implementation.listen(event);
    }

}