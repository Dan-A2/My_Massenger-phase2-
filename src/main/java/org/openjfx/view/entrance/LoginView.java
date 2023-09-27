package org.openjfx.view.entrance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class LoginView{

    @FXML
    private Button loginButton;
    @FXML
    private Button switchToRegisterButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    void loginPressed(ActionEvent event) {
        loginCommandListener.listenCommand(COMMANDS.GOTOLOGIN);
    }
    @FXML
    void registerPressed(ActionEvent event){
        loginCommandListener.listenCommand(COMMANDS.GOTOREGISTER);
    }

    private CommandListener loginCommandListener;

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setLoginStringListener(CommandListener loginCommandListener) {
        this.loginCommandListener = loginCommandListener;
    }
}