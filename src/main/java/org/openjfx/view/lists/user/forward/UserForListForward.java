package org.openjfx.view.lists.user.forward;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.event.ForwardEvent;
import org.openjfx.listeners.massages.ForwardListenerImplementation;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.Massage;
import org.openjfx.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.view.COMMANDS;

public class UserForListForward {

    static private final Logger logger = (Logger) LogManager.getLogger(UserForListForward.class);
    private FXMLLoader loader;
    private HBox hBox;
    private User forwardFrom;

    public UserForListForward(User forwardFrom, User forwardTo, String massage, int imageId) {

        Config config = Config.getConfig("lists");
        try {
            loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"userForwardAddress")));
            this.forwardFrom = forwardFrom;
            hBox = loader.load();
            UserForListForwardView view = loader.getController();
            view.getUsernameLabel().setText(forwardTo.getUsername());
            view.setListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if(command == COMMANDS.FORWARD){
                        ForwardEvent event = new ForwardEvent(forwardTo, forwardFrom, massage, imageId);
                        ForwardListenerImplementation implementation = new ForwardListenerImplementation();
                        implementation.listenForward(event);
                    }
                }
            });
            logger.info(config.getProperty(String.class,"userInfo"));
        } catch (Exception e){
            logger.warn(config.getProperty(String.class,"userWarn"));
            e.printStackTrace();
        }

    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public HBox gethBox() {
        return hBox;
    }
}