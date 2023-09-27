package org.openjfx.view.entrance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.event.LoginEvent;
import org.openjfx.listeners.CommandListener;
import org.openjfx.listeners.entrance.LoginListenerImplementation;
import org.openjfx.view.COMMANDS;

public class Login {

    private Config config = Config.getConfig("registration");
    static private final Logger logger = (Logger) LogManager.getLogger(Login.class);

    public void generate() {

        FXMLLoader loginLoader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"loginAddress")));
        try {
            Parent loginRoot = loginLoader.load();
            SceneManager.setLoginScene(new Scene(loginRoot));
            SceneManager.getMainStage().setScene(SceneManager.getLoginScene());
            setLoginListeners(loginLoader);
            SceneManager.getMainStage().show();
            logger.info(config.getProperty(String.class, "infoLogin"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"warnLogin"));
            e.printStackTrace();
        }

    }

    private void setLoginListeners(FXMLLoader loader){
        LoginView currentView = (LoginView) loader.getController();
        currentView.setLoginStringListener(new CommandListener() {
            @Override
            public void listenCommand(COMMANDS command) {
                if(command == COMMANDS.GOTOREGISTER){
                    try {
                        SceneManager.switchToRegister();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else if(command == COMMANDS.GOTOLOGIN) {
                    LoginListenerImplementation implementation = new LoginListenerImplementation();
                    LoginEvent event1 = new LoginEvent(currentView.getUsernameField().getText(), currentView.getPasswordField().getText());
                    implementation.listen(event1);
                    if(!implementation.getLoginController().isInputValid(currentView.getUsernameField().getText(), currentView.getPasswordField().getText())){
                        currentView.getErrorLabel().setText(config.getProperty(String.class,"notFoundError"));
                    } else {
                        currentView.getUsernameField().clear();
                        currentView.getPasswordField().clear();
                        currentView.getErrorLabel().setText("");
                    }
                }
            }
        });
    }

}