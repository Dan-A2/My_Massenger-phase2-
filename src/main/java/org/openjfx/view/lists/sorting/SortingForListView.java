package org.openjfx.view.lists.sorting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.openjfx.controller.Config;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class SortingForListView {

    @FXML
    private HBox hBox;

    @FXML
    private Label sortingNameLabel;

    @FXML
    private Button viewBtn;

    @FXML
    private Button removeBtn;

    @FXML
    void removeSorting(ActionEvent event) {
        sortingListener.listenCommand(COMMANDS.REMOVE);
    }

    @FXML
    void view(ActionEvent event) {
        sortingListener.listenCommand(COMMANDS.VIEW);
    }

    private CommandListener sortingListener;

    public void setSortingListener(CommandListener sortingListener) {
        this.sortingListener = sortingListener;
    }

    public Label getSortingNameLabel() {
        return sortingNameLabel;
    }
}