package org.openjfx.view.explorer.search;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.UserController;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.mainMenu.Mainmenu;
import org.openjfx.view.watchUserPage.WatchUserPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class Search {

    static private final Logger logger = (Logger) LogManager.getLogger(Search.class);

    public void show() {
        Config searchConfig = Config.getConfig("explorer");
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(searchConfig.getProperty(String.class, "searchAddress")));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            SearchView view = loader.getController();
            view.setSearchListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if (command == COMMANDS.BACK) {
                        SceneManager.getSceneManager().getBack();
                    } else if (command == COMMANDS.MAINMENU) {
                        SceneManager.getSceneManager().backToMain();
                    } else if (command == COMMANDS.SEARCH) {
                        UserController controller = new UserController();
                        String username = view.getUsernameField().getText();
                        User user = controller.find(username);
                        if (user == null || Mainmenu.getMenuView().getCurrentUser().getId() == user.getId()) {
                            JOptionPane.showMessageDialog(null, searchConfig.getProperty(String.class, "invalidUsername"));
                        } else {
                            WatchUserPage watchUserPage = new WatchUserPage(user, Mainmenu.getMenuView().getCurrentUser());
                            watchUserPage.show();
                        }
                    }
                }
            });
            logger.info(searchConfig.getProperty(String.class,"searchInfo"));
            SceneManager.getSceneManager().push(scene);
        } catch (Exception e) {
            logger.warn(searchConfig.getProperty(String.class,"searchWarn"));
            e.printStackTrace();
        }
    }

}