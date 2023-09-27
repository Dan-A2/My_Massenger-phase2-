package org.openjfx.view.mainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.openjfx.controller.Config;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class MainmenuView {

    @FXML
    private Button yourAccountBtn;
    @FXML
    private Button timelineBtn;
    @FXML
    private Button explorerBtn;
    @FXML
    private Button massagingBtn;
    @FXML
    private Button settingBtn;
    @FXML
    private AnchorPane mainPane;

    @FXML
    void explorerPressed(ActionEvent event) {
        mainMenuListener.listenCommand(COMMANDS.GOTOEXPLORER);
    }

    @FXML
    void massagingPressed(ActionEvent event) {
        mainMenuListener.listenCommand(COMMANDS.GOTOMASSAGING);
    }

    @FXML
    void settingPressed(ActionEvent event) {
        mainMenuListener.listenCommand(COMMANDS.GOTOSETTING);
    }

    @FXML
    void timeLinePressed(ActionEvent event) {
        mainMenuListener.listenCommand(COMMANDS.GOTOTIMELINE);
    }

    @FXML
    void yourAccountPressed(ActionEvent event) {
        mainMenuListener.listenCommand(COMMANDS.GOTOPROFILE);
    }

    private CommandListener mainMenuListener;

    public void setMainMenuListener(CommandListener mainMenuListener) {
        this.mainMenuListener = mainMenuListener;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }
}