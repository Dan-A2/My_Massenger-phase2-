package org.openjfx.view.chat.massage.withUserPic;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class MassageWithUserPicView {

    @FXML
    private AnchorPane pane;

    @FXML
    private Circle circle;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label massageLabel;

    @FXML
    private FontAwesomeIconView deleteBtn;

    @FXML
    private FontAwesomeIconView editBtn;

    @FXML
    private FontAwesomeIconView saveBtn;

    @FXML
    void deleteMassage(MouseEvent event) {
        listener.listenCommand(COMMANDS.DELETE);
    }

    @FXML
    void edit(MouseEvent event) {
        listener.listenCommand(COMMANDS.EDIT);
    }

    @FXML
    void saveMassage(MouseEvent event) {
        System.out.println(listener);
        listener.listenCommand(COMMANDS.SAVE);
    }

    private CommandListener listener;

    public void setListener(CommandListener listener) {
        this.listener = listener;
    }

    public Circle getCircle() {
        return circle;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public Label getMassageLabel() {
        return massageLabel;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public FontAwesomeIconView getDeleteBtn() {
        return deleteBtn;
    }

    public FontAwesomeIconView getEditBtn() {
        return editBtn;
    }

    public FontAwesomeIconView getSaveBtn() {
        return saveBtn;
    }
}