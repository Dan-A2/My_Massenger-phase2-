package org.openjfx.view.chat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.event.EditMassageEvent;
import org.openjfx.listeners.CommandListener;
import org.openjfx.listeners.massages.EditDeleteMassageListenerImplementation;
import org.openjfx.models.Massage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.view.COMMANDS;

import javax.swing.*;

public class Edit {

    static private final Logger logger = (Logger) LogManager.getLogger(Edit.class);

    public void show(Massage massage) {

        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(Config.getConfig("chat").getProperty(String.class,"editAddress")));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            EditView view = loader.getController();
            setListener(view, massage);
            SceneManager.getSceneManager().push(scene);
            logger.info(Config.getConfig("chat").getProperty(String.class,"editInfo"));
        } catch (Exception e) {
            logger.info(Config.getConfig("chat").getProperty(String.class,"editWarn"));
            e.printStackTrace();
        }

    }

    private void setListener(EditView view, Massage massage){
        view.setListener(new CommandListener() {
            @Override
            public void listenCommand(COMMANDS commands) {
                if (commands == COMMANDS.BACK) {
                    SceneManager.getSceneManager().getBack();
                } else if (commands == COMMANDS.MAINMENU) {
                    SceneManager.getSceneManager().backToMain();
                } else if (commands == COMMANDS.EDIT) {
                    if (view.getMassageField().getText().equals("")) {
                        JOptionPane.showMessageDialog(null, Config.getConfig("chat").getProperty(String.class,"editErrorMassage"));
                    } else {
                        EditMassageEvent event = new EditMassageEvent(massage, view.getMassageField().getText());
                        EditDeleteMassageListenerImplementation implementation = new EditDeleteMassageListenerImplementation();
                        implementation.listenEdit(event);
                        view.getMassageField().clear();
                    }
                }
            }
        });
    }

}