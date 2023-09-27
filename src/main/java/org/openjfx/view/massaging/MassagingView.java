package org.openjfx.view.massaging;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class MassagingView {

    @FXML
    private Button savedMassagesBtn;

    @FXML
    private Button sendMassageToSortingBtn;

    @FXML
    private Button savedTweetsBtn;

    @FXML
    private Button createSortingBtn;

    @FXML
    private Button chatsBtn;

    @FXML
    private Button notesBtn;

    @FXML
    private Button groupsBtn;

    @FXML
    private Button createGroupBtn;

    @FXML
    private Button addNoteBtn;

    @FXML
    void addNote(ActionEvent event) {
        massagingListener.listenCommand(COMMANDS.ADDNOTE);
    }

    @FXML
    void chatsPressed(ActionEvent event) {
        massagingListener.listenCommand(COMMANDS.GOTOCHATS);
    }

    @FXML
    void createSortingPressed(ActionEvent event) {
        massagingListener.listenCommand(COMMANDS.CREATESORTING);
    }

    @FXML
    void notesPressed(ActionEvent event) {
        massagingListener.listenCommand(COMMANDS.GOTONOTES);
    }

    @FXML
    void savedMassagesPressed(ActionEvent event) {
        massagingListener.listenCommand(COMMANDS.GOTOSAVEDMASSAGES);
    }

    @FXML
    void savedTweetsPressed(ActionEvent event) {
        massagingListener.listenCommand(COMMANDS.GOTOSAVEDTWEETS);
    }

    @FXML
    void sendMassageToSortingPressed(ActionEvent event) {
        massagingListener.listenCommand(COMMANDS.SENDMASSAGETOSORTING);
    }

    @FXML
    void createGroupPressed(ActionEvent event) {
        massagingListener.listenCommand(COMMANDS.CREATEGROUP);
    }

    @FXML
    void groupsPressed(ActionEvent event) {
        massagingListener.listenCommand(COMMANDS.SHOWGROUPS);
    }

    private CommandListener massagingListener;

    public void setMassagingListener(CommandListener massagingListener) {
        this.massagingListener = massagingListener;
    }
}