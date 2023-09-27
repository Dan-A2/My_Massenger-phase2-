package org.openjfx.view.chat.massage;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.openjfx.listeners.CommandListener;
import org.openjfx.view.COMMANDS;

public class MassageView {

    @FXML
    private AnchorPane chatPane;

    @FXML
    private Label massageLabel;

    @FXML
    private FontAwesomeIconView deleteBtn;

    @FXML
    void delete(MouseEvent event) {
        listener.listenCommand(COMMANDS.DELETE);
    }

    private CommandListener listener;

    public void setListener(CommandListener listener) {
        this.listener = listener;
    }

    public AnchorPane getChatPane() {
        return chatPane;
    }

    public Label getMassageLabel() {
        return massageLabel;
    }
}