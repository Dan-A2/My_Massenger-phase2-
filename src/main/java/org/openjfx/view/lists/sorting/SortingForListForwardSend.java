package org.openjfx.view.lists.sorting;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.event.ForwardToSortingEvent;
import org.openjfx.listeners.massages.ForwardToSortingListenerImplementation;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.view.COMMANDS;

public class SortingForListForwardSend {

    static private final Logger logger = (Logger) LogManager.getLogger(SortingForListForwardSend.class);
    private HBox hBox;

    public SortingForListForwardSend(User forwardFrom, String sortingName, String massage, String forwardSend, boolean isForwarded, int imageId) {
        Config config = Config.getConfig("lists");
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"sortingForwardAddress")));
            hBox = loader.load();
            SortingForListForwardSendView view = loader.getController();
            view.getSortingNameLabel().setText(sortingName);
            if (forwardSend.equals(config.getProperty(String.class,"forward"))){
                view.getForwardSendBtn().setText(config.getProperty(String.class,"forward"));
            } else {
                view.getForwardSendBtn().setText(config.getProperty(String.class,"send"));
            }
            view.setSortingForward(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if(command == COMMANDS.FORWARD){
                        ForwardToSortingEvent event = new ForwardToSortingEvent(sortingName, forwardFrom, massage, isForwarded, imageId);
                        ForwardToSortingListenerImplementation implementation = new ForwardToSortingListenerImplementation();
                        implementation.listen(event);
                    }
                }
            });
            logger.info(config.getProperty(String.class,"sortingInfo"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"sortingWarn"));
            e.printStackTrace();
        }
    }

    public HBox gethBox() {
        return hBox;
    }
}