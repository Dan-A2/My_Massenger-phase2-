package org.openjfx.view.lists.user.requester;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.event.DoRequestEvent;
import org.openjfx.listeners.CommandListener;
import org.openjfx.listeners.users.DoRequestListenerImplementation;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.apache.logging.log4j.Logger;

public class UserForListRequest {

    static private final Logger logger = (Logger) LogManager.getLogger(UserForListRequest.class);
    private AnchorPane pane;
    private CommandListener listener;

    public void create(User user1, User requester) {

        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(Config.getConfig("lists").getProperty(String.class, "userRequestAddress")));
            pane = loader.load();
            UserForListRequestView view = loader.getController();
            view.getUsernameLabel().setText(requester.getUsername());
            view.setListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    DoRequestListenerImplementation implementation = new DoRequestListenerImplementation();
                    DoRequestEvent event = new DoRequestEvent(user1, requester, command);
                    implementation.listenRequest(event);
                    listener.listenCommand(command);
                }
            });
            logger.info(Config.getConfig("lists").getProperty(String.class,"userRequestInfo"));
        } catch (Exception e) {
            logger.warn(Config.getConfig("lists").getProperty(String.class,"userRequestWarn"));
            e.printStackTrace();
        }

    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setListener(CommandListener listener) {
        this.listener = listener;
    }
}