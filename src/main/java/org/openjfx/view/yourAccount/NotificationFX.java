package org.openjfx.view.yourAccount;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.models.Notification;

public class NotificationFX {

    private AnchorPane pane;

    public void create(Notification notification){

        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(Config.getConfig("yourAccount").getProperty(String.class, "notificationAddress")));
        try {
            pane = loader.load();
            NotificationView view = loader.getController();
            view.getNotificationLabel().setText(notification.getNotif());
            view.getTimeLabel().setText(notification.getDateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public AnchorPane getPane() {
        return pane;
    }
}