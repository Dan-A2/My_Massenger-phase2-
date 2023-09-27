package org.openjfx.view.setting;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.UserController;
import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.event.ChangeSettingsEvent;
import org.openjfx.event.DeleteUserEvent;
import org.openjfx.listeners.users.ChangeSettingListenerImplementation;
import org.openjfx.listeners.users.DeleteUserListenerImplementation;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Setting {

    static private final Logger logger = (Logger) LogManager.getLogger(Setting.class);
    private User user;
    private AnchorPane pane;
    private Config config = Config.getConfig("setting");

    public Setting(User user) {
        this.user = user;
    }

    public void create() {

        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"address")));
        try {
            pane = loader.load();
            SettingView view = loader.getController();
            update(view);
            setListener(view);
            logger.info(config.getProperty(String.class, "info"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"warn"));
            e.printStackTrace();
        }

    }

    private void setListener(SettingView view) {
        view.setSettingListener(new CommandListener() {
            @Override
            public void listenCommand(COMMANDS command) {
                if (command == COMMANDS.EDIT) {
                    view.getEditBtn().setVisible(false);
                    view.getSaveBtn().setVisible(true);
                } else if (command == COMMANDS.LOGOUT) {
                    UserController controller = new UserController();
                    controller.setLastSeen(user);
                    SceneManager.getSceneManager().clear();
                    SceneManager.getMainStage().setScene(SceneManager.getLoginScene());
                } else if (command == COMMANDS.SAVE) {
                    view.getEditBtn().setVisible(true);
                    view.getSaveBtn().setVisible(false);
                    save(view);
                } else if (command == COMMANDS.DELETE) {
                    DeleteUserEvent event = new DeleteUserEvent(user);
                    DeleteUserListenerImplementation implementation = new DeleteUserListenerImplementation();
                    implementation.listen(event);
                    SceneManager.getMainStage().setScene(SceneManager.getLoginScene());
                }
            }
        });
    }

    private void save(SettingView view) {
        boolean accountPublicity;
        boolean accountActivity;
        String whoCanSeeL = "";
        accountPublicity = view.getPublicRadioBtn().isSelected();
        if (view.getEveryOneRBtn().isSelected()) {
            whoCanSeeL = config.getProperty(String.class,"everybody");
        } else if (view.getFollowersRBtn().isSelected()) {
            whoCanSeeL = config.getProperty(String.class,"followers");
        } else if (view.getNobodyRBtn().isSelected()){
            whoCanSeeL = config.getProperty(String.class,"nobody");
        }
        accountActivity = view.getActiveRBtn().isSelected();
        ChangeSettingsEvent event = new ChangeSettingsEvent(user, accountPublicity, whoCanSeeL, accountActivity);
        ChangeSettingListenerImplementation implementation = new ChangeSettingListenerImplementation();
        implementation.change(event);
    }

    private void update(SettingView view) {
        if (user.isAccountPublic()) {
            view.getPublicRadioBtn().setSelected(true);
        } else {
            view.getPrivateRadioBtn().setSelected(true);
        }
        if (user.getWhoCanSeeLastSeen().equals(config.getProperty(String.class,"everybody"))) {
            view.getEveryOneRBtn().setSelected(true);
        } else if (user.getWhoCanSeeLastSeen().equals(config.getProperty(String.class,"followers"))) {
            view.getFollowersRBtn().setSelected(true);
        } else { //nobody
            view.getNobodyRBtn().setSelected(true);
        }
        if (user.isActive()) {
            view.getActiveRBtn().setSelected(true);
        } else {
            view.getInactiveRBtn().setSelected(true);
        }
    }

    public AnchorPane getPane() {
        return pane;
    }
}