package org.openjfx.view.entrance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.event.RegistrationEvent;
import org.openjfx.listeners.CommandListener;
import org.openjfx.listeners.entrance.RegistrationListenerImplementation;
import org.openjfx.validation.CheckValidity;
import org.openjfx.view.COMMANDS;

public class Register {

    static private final Logger logger = (Logger) LogManager.getLogger(Register.class);
    private Config config = Config.getConfig("registration");

    public void generate(){

        try {
            FXMLLoader registerLoader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class, "registerAddress")));
            Parent registerRoot = registerLoader.load();
            SceneManager.setRegisterScene(new Scene(registerRoot));
            setRegisterListeners(registerLoader);
            logger.info(config.getProperty(String.class, "infoRegistration"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class, "warnRegistration"));
            e.printStackTrace();
        }
    }

    private void setRegisterListeners(FXMLLoader loader){
        RegisterView currentView = (RegisterView) loader.getController();
        currentView.setRegisterStringListener(new CommandListener() {
            @Override
            public void listenCommand(COMMANDS command) {
                if(command == COMMANDS.GOTOLOGIN){
                    try {
                        SceneManager.switchToLogin();
                    } catch (Exception e){e.printStackTrace();}
                } else if(command == COMMANDS.GOTOREGISTER){
                    String firstname = currentView.getFirstnameField().getText();
                    String lastname = currentView.getLastnameField().getText();
                    String username = currentView.getUsernameField().getText();
                    String password = currentView.getPasswordField().getText();
                    String email = currentView.getEmailField().getText();
                    String bio = currentView.getBioArea().getText();
                    String phoneNumber = currentView.getPhoneNumberField().getText();
                    if(firstname.equals("") || lastname.equals("") || username.equals("") || password.equals("") || email.equals("")) {
                        currentView.getErrorLabel().setText(config.getProperty(String.class,"emptyFields"));
                    } else if(!CheckValidity.isNameValid(firstname) || !CheckValidity.isNameValid(lastname)){
                        currentView.getErrorLabel().setText(config.getProperty(String.class,"numberError"));
                    } else if(!CheckValidity.isUsernameValid(username) || CheckValidity.isUsernameRepeated(username)){
                        currentView.getErrorLabel().setText(config.getProperty(String.class,"usernameError"));
                    } else if(CheckValidity.isEmailRepeated(email)){
                        currentView.getErrorLabel().setText(config.getProperty(String.class,"emailError"));
                    } else if(!phoneNumber.equals("") && (!CheckValidity.isNumberValid(phoneNumber) || CheckValidity.isPhoneNumberRepeated(phoneNumber))){
                        currentView.getErrorLabel().setText(config.getProperty(String.class,"phoneNumberError"));
                    } else {
                        RegistrationListenerImplementation implementation = new RegistrationListenerImplementation();
                        RegistrationEvent event1 = new RegistrationEvent(firstname, lastname, username,
                                password, email, bio, phoneNumber,
                                currentView.getBirthdayPicker().getValue());
                        implementation.listen(event1);
                        currentView.getLastnameField().clear();
                        currentView.getUsernameField().clear();
                        currentView.getUsernameField().clear();
                        currentView.getPasswordField().clear();
                        currentView.getEmailField().clear();
                        currentView.getBioArea().clear();
                        currentView.getPhoneNumberField().clear();
                        currentView.getBirthdayPicker().setValue(null);
                        currentView.getErrorLabel().setText("");
                    }
                }
            }
        });
    }

}