package org.openjfx.view.lists.sorting;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.event.CreateSortingEvent;
import org.openjfx.listeners.sortings.Create_RemoveSortingLI;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.lists.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortingForList {

    static private final Logger logger = (Logger) LogManager.getLogger(SortingForList.class);
    private FXMLLoader loader;
    private HBox hBox;
    private User user;
    private String sortingName;

    public SortingForList(User user, String sortingName) {
        Config config = Config.getConfig("lists");
        this.user = user;
        this.sortingName = sortingName;
        try {
            loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"sortingAddress")));
            hBox = loader.load();
            SortingForListView currentView = loader.getController();
            currentView.getSortingNameLabel().setText(sortingName);
            currentView.setSortingListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if(command == COMMANDS.VIEW){
                        Lists lists = new Lists();
                        lists.showUsersOfSorting(user, user.getMySortings().get(sortingName), sortingName);
                    } else if (command == COMMANDS.REMOVE){
                        CreateSortingEvent event = new CreateSortingEvent(user.getMySortings().get(sortingName), sortingName, user);
                        Create_RemoveSortingLI implementation = new Create_RemoveSortingLI();
                        implementation.listenRemove(event);
                    }
                }
            });
            logger.info(config.getProperty(String.class,"sortingInfo"));
        } catch (Exception e){
            logger.warn(config.getProperty(String.class,"sortingWarn"));
            e.printStackTrace();
        }
    }

    public HBox gethBox() {
        return hBox;
    }
}