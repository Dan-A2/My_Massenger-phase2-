package org.openjfx.view.chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class ChatView {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Circle pictureCircle;

    @FXML
    private Label nameLabel;

    @FXML
    private Label numberOfMembers;

    @FXML
    private GridPane chatGrid;

    @FXML
    private Button sendBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TextArea massageArea;

    @FXML
    private Button mainMenuBtn;

    @FXML
    private Button attachBtn;

    @FXML
    private Button addUserBtn;

    @FXML
    void addUser(ActionEvent event) {
        chatListener.listenCommand(COMMANDS.ADDMEMBER);
    }

    @FXML
    void sendMassage(ActionEvent event) {
        chatListener.listenCommand(COMMANDS.SENDMASSAGE);
    }

    @FXML
    void getBack(ActionEvent event) {
        chatListener.listenCommand(COMMANDS.BACK);
    }

    @FXML
    void getBackMM(ActionEvent event) {
        chatListener.listenCommand(COMMANDS.MAINMENU);
    }

    @FXML
    void attach(ActionEvent event) {
        chatListener.listenCommand(COMMANDS.SELECTIMAGE);
    }

    private CommandListener chatListener;

    public void setChatListener(CommandListener chatListener) {
        this.chatListener = chatListener;
    }

    public TextArea getMassageArea() {
        return massageArea;
    }

    public Circle getPictureCircle() {
        return pictureCircle;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public Label getNumberOfMembers() {
        return numberOfMembers;
    }

    public GridPane getChatGrid() {
        return chatGrid;
    }

    public Button getSendBtn() {
        return sendBtn;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public Button getBackBtn() {
        return backBtn;
    }

    public Button getAddUserBtn() {
        return addUserBtn;
    }
}