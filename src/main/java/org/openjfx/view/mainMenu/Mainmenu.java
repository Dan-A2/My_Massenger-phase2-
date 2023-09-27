package org.openjfx.view.mainMenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.explorer.Explorer;
import org.openjfx.view.massaging.Massaging;
import org.openjfx.view.setting.Setting;
import org.openjfx.view.timeline.Timeline;
import org.openjfx.view.yourAccount.YourAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mainmenu {

    static private final Logger logger = (Logger) LogManager.getLogger(Mainmenu.class);
    private static Mainmenu menuView;
    private FXMLLoader mainmenuLoader;
    private Parent mainmenuRoot;
    private Scene menuScene;
    private User currentUser;

    public Mainmenu(User user) {
        Config config = Config.getConfig("mainmenu");
        menuView = this;
        currentUser = user;
        try {
            mainmenuLoader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"address")));
            mainmenuRoot = mainmenuLoader.load();
            menuScene = new Scene(mainmenuRoot);
            MainmenuView currentView = mainmenuLoader.getController();
            AnchorPane pane = currentView.getMainPane();
            currentView.setMainMenuListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if (command == COMMANDS.GOTOPROFILE) {
                        pane.getChildren().clear();
                        YourAccount yourAccount = new YourAccount(currentUser);
                        yourAccount.create();
                        pane.getChildren().add(yourAccount.getPane());
                        SceneManager.getSceneManager().push(menuScene);
                    } else if(command == COMMANDS.GOTOEXPLORER) {
                        pane.getChildren().clear();
                        Explorer explorer = new Explorer(currentUser);
                        explorer.show();
                        pane.getChildren().add(explorer.getPane());
                        SceneManager.getSceneManager().push(menuScene);
                    } else if(command == COMMANDS.GOTOTIMELINE) {
                        pane.getChildren().clear();
                        Timeline timeline = new Timeline(currentUser);
                        timeline.generate();
                        pane.getChildren().add(timeline.getPane());
                        SceneManager.getSceneManager().push(menuScene);
                    } else if(command == COMMANDS.GOTOMASSAGING) {
                        pane.getChildren().clear();
                        Massaging massaging = new Massaging(currentUser);
                        massaging.generate();
                        pane.getChildren().add(massaging.getPane());
                        SceneManager.getSceneManager().push(menuScene);
                    } else if(command == COMMANDS.GOTOSETTING) {
                        pane.getChildren().clear();
                        Setting setting = new Setting(currentUser);
                        setting.create();
                        pane.getChildren().add(setting.getPane());
                        SceneManager.getSceneManager().push(menuScene);
                    }
                }
            });
            SceneManager.getSceneManager().push(menuScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Mainmenu getMenuView() {
        return menuView;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
