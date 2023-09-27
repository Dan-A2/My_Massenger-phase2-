package org.openjfx.view.setting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class SettingView {

    @FXML
    private AnchorPane settingPane;

    @FXML
    private RadioButton privateRadioBtn;

    @FXML
    private ToggleGroup privacy;

    @FXML
    private RadioButton publicRadioBtn;

    @FXML
    private RadioButton everyOneRBtn;

    @FXML
    private ToggleGroup lastseen;

    @FXML
    private RadioButton followersRBtn;

    @FXML
    private RadioButton nobodyRBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private RadioButton activeRBtn;

    @FXML
    private ToggleGroup activity;

    @FXML
    private RadioButton inactiveRBtn;


    @FXML
    private Button editBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button deleteAccountBtn;

    @FXML
    void deleteAccount(ActionEvent event) {
        settingListener.listenCommand(COMMANDS.DELETE);
    }

    @FXML
    void editPressed(ActionEvent event) {
        settingListener.listenCommand(COMMANDS.EDIT);
    }

    @FXML
    void logout(ActionEvent event) {
        settingListener.listenCommand(COMMANDS.LOGOUT);
    }

    @FXML
    void saveChanges(ActionEvent event) {
        settingListener.listenCommand(COMMANDS.SAVE);
    }

    private CommandListener settingListener;

    public void setSettingListener(CommandListener settingListener) {
        this.settingListener = settingListener;
    }

    public RadioButton getPrivateRadioBtn() {
        return privateRadioBtn;
    }

    public RadioButton getPublicRadioBtn() {
        return publicRadioBtn;
    }

    public RadioButton getEveryOneRBtn() {
        return everyOneRBtn;
    }

    public RadioButton getFollowersRBtn() {
        return followersRBtn;
    }

    public RadioButton getNobodyRBtn() {
        return nobodyRBtn;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public Button getEditBtn() {
        return editBtn;
    }

    public RadioButton getActiveRBtn() {
        return activeRBtn;
    }

    public RadioButton getInactiveRBtn() {
        return inactiveRBtn;
    }
}