package org.openjfx.view.entrance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class RegisterView {

    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private TextArea bioArea;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private DatePicker birthdayPicker;
    @FXML
    private Button registerBtn;
    @FXML
    private Button switchToLoginButton;
    @FXML
    private Label errorLabel;
    @FXML
    void loginPressed(ActionEvent event) {
        registerCommandListener.listenCommand(COMMANDS.GOTOLOGIN);
    }
    @FXML
    void registerPressed(ActionEvent event){
        registerCommandListener.listenCommand(COMMANDS.GOTOREGISTER);
    }

    private CommandListener registerCommandListener;

    public void setRegisterStringListener(CommandListener registerCommandListener) {
        this.registerCommandListener = registerCommandListener;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TextField getFirstnameField() {
        return firstnameField;
    }

    public TextField getLastnameField() {
        return lastnameField;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextArea getBioArea() {
        return bioArea;
    }

    public TextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public DatePicker getBirthdayPicker() {
        return birthdayPicker;
    }
}