package org.openjfx.view.chat.massage;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class MassageFX {

    static private final Logger logger = (Logger) LogManager.getLogger(MassageFX.class);
    private String txt;
    private AnchorPane massagePane;
    private CommandListener listener;

    public MassageFX(String txt) {
        Config massageFXConfig = Config.getConfig("chat");
        this.txt = txt;
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(massageFXConfig.getProperty(String.class, "massageFXAddress")));
        try {
            massagePane = loader.load();
            MassageView view = loader.getController();
            view.setListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    listener.listenCommand(command);
                }
            });
            logger.info(massageFXConfig.getProperty(String.class,"massageFXInfo"));
        } catch (Exception e) {
            logger.warn(massageFXConfig.getProperty(String.class,"massageFXWarn"));
            e.printStackTrace();
        }
        MassageView view = loader.getController();
        view.getMassageLabel().setText(txt);
    }

    public String getTxt() {
        return txt;
    }

    public AnchorPane getMassagePane() {
        return massagePane;
    }

    public void setListener(CommandListener listener) {
        this.listener = listener;
    }
}