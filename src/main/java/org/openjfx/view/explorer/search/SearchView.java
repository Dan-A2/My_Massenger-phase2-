package org.openjfx.view.explorer.search;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class SearchView {

    @FXML
    private TextField usernameField;

    @FXML
    private FontAwesomeIconView searchBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button mainMenuBtn;

    @FXML
    void getBack(ActionEvent event) {
        searchListener.listenCommand(COMMANDS.BACK);
    }

    @FXML
    void getBackMM(ActionEvent event) {
        searchListener.listenCommand(COMMANDS.MAINMENU);
    }

    @FXML
    void searchUser(MouseEvent event) {
        searchListener.listenCommand(COMMANDS.SEARCH);
    }

    private CommandListener searchListener;

    public void setSearchListener(CommandListener searchListener) {
        this.searchListener = searchListener;
    }

    public TextField getUsernameField() {
        return usernameField;
    }
}