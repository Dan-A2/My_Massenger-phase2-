package org.openjfx.view.yourAccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class ProfileView {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private TextField phonenumberField;

    @FXML
    private TextArea bioArea;

    @FXML
    private Button editBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button back;

    @FXML
    private Button setProfBtn;

    @FXML
    private TextField passwordField;

    @FXML
    private Circle profileCircle;

    @FXML
    private Label birthdayLabel;


    @FXML
    void editPressed(ActionEvent event) {
        profileListener.listenCommand(COMMANDS.EDIT);
    }

    @FXML
    void getBack(ActionEvent event) {
        profileListener.listenCommand(COMMANDS.BACK);
    }

    @FXML
    void savePressed(ActionEvent event) {
        profileListener.listenCommand(COMMANDS.SAVE);
    }

    @FXML
    void selectImage(ActionEvent event) {
        profileListener.listenCommand(COMMANDS.SELECTIMAGE);
    }

    private CommandListener profileListener;

    public void setProfileListener(CommandListener profileListener) {
        this.profileListener = profileListener;
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

    public TextField getEmailField() {
        return emailField;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public DatePicker getBirthdayPicker() {
        return birthdayPicker;
    }

    public TextField getPhonenumberField() {
        return phonenumberField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextArea getBioArea() {
        return bioArea;
    }

    public Button getEditBtn() {
        return editBtn;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public Circle getProfileCircle() {
        return profileCircle;
    }

    public Label getBirthdayLabel() {
        return birthdayLabel;
    }

    public Button getSetProfBtn() {
        return setProfBtn;
    }
}