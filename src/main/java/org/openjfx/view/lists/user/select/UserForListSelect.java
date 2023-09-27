package org.openjfx.view.lists.user.select;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.view.COMMANDS;

public class UserForListSelect {

    static private final Logger logger = (Logger) LogManager.getLogger(UserForListSelect.class);
    private HBox box;
    private boolean selected;
    private User user;

    public void create(User user){

        Config config = Config.getConfig("lists");
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"userSelectAddress")));
        selected = false;
        try {
            box = loader.load();
            UserForListSelectView view = loader.getController();
            update(view);
            view.getUsernameLabel().setText(user.getUsername());
            view.setListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if (command == COMMANDS.SELECT) {
                        selected = true;
                    } else if (command == COMMANDS.DESELECT) {
                        selected = false;
                    }
                    selectListener.listenCommand(command);
                    update(view);
                }
            });
            logger.info(config.getProperty(String.class,"userInfo"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"userWarn"));
            e.printStackTrace();
        }

    }

    private void update(UserForListSelectView view){

        if (selected) {
            view.getSelectBtn().setVisible(false);
            view.getDeselectBtn().setVisible(true);
        } else {
            view.getSelectBtn().setVisible(true);
            view.getDeselectBtn().setVisible(false);
        }

    }

    private CommandListener selectListener;

    public void setSelectListener(CommandListener selectListener) {
        this.selectListener = selectListener;
    }

    public HBox getBox() {
        return box;
    }
}