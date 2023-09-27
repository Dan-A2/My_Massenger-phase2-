package org.openjfx.view.lists;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class ListSelectSortingGroupView {

    @FXML
    private Label typeLabel;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vBox;

    @FXML
    private Button backBtn;

    @FXML
    private Button mainMenuBtn;

    @FXML
    private TextField sortingNameField;

    @FXML
    private Button createBtn;

    @FXML
    void createSorting(ActionEvent event) {
        listSelectSortingListener.listenCommand(COMMANDS.CREATE);
    }

    @FXML
    void getBack(ActionEvent event) {
        listSelectSortingListener.listenCommand(COMMANDS.BACK);
    }

    @FXML
    void getBackMM(ActionEvent event) {
        listSelectSortingListener.listenCommand(COMMANDS.MAINMENU);
    }

    private CommandListener listSelectSortingListener;

    public void setListener(CommandListener listener) {
        this.listSelectSortingListener = listener;
    }

    public Label getTypeLabel() {
        return typeLabel;
    }

    public TextField getSortingNameField() {
        return sortingNameField;
    }

    public VBox getvBox() {
        return vBox;
    }

    public Button getCreateBtn() {
        return createBtn;
    }
}