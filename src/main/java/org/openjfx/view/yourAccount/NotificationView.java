package org.openjfx.view.yourAccount;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class NotificationView {

    @FXML
    private AnchorPane pane;

    @FXML
    private Label notificationLabel;

    @FXML
    private Label timeLabel;


    public Label getNotificationLabel() {
        return notificationLabel;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }
}