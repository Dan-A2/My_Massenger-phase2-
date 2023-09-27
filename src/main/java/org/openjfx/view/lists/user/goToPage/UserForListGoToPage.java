package org.openjfx.view.lists.user.goToPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.event.WatchUserPageEvent;
import org.openjfx.listeners.CommandListener;
import org.openjfx.listeners.watchSomeonePage.WSPListenerImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.view.COMMANDS;

public class UserForListGoToPage {

    static private final Logger logger = (Logger) LogManager.getLogger(UserForListGoToPage.class);
    private HBox box;

    public void createUser(String showTo, String showFrom){
        Config config = Config.getConfig("lists");
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"userGoToAddress")));
            box = loader.load();
            UserForListGoToPageView componentController = loader.getController();
            componentController.getUsernameLabel().setText(showFrom);
            WSPListenerImplementation implementation = new WSPListenerImplementation();
            componentController.setListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if(command == COMMANDS.GOTOPAGE){
                        WatchUserPageEvent event = new WatchUserPageEvent(showTo, showFrom);
                        implementation.listen(event);
                    }
                }
            });
            logger.info(config.getProperty(String.class,"userInfo"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"userWarn"));
            e.printStackTrace();
        }
    }

    public HBox getBox() {
        return box;
    }
}
