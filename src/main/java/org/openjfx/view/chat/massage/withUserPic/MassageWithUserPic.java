package org.openjfx.view.chat.massage.withUserPic;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.ImageController;
import org.openjfx.models.Massage;
import org.openjfx.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MassageWithUserPic {

    static private final Logger logger = (Logger) LogManager.getLogger(MassageWithUserPic.class);
    private User sender;
    private Massage massage;
    private AnchorPane pane;
    private MassageWithUserPicView view;

    public MassageWithUserPic(User sender, Massage massage) {
        this.sender = sender;
        this.massage = massage;
    }

    public void create(String type) {
        Config massageWithPicConfig = Config.getConfig("chat");
        FXMLLoader loader1 = new FXMLLoader(SceneManager.class.getResource(massageWithPicConfig.getProperty(String.class,"massageWithPicAddress")));
        FXMLLoader loader2 = new FXMLLoader(SceneManager.class.getResource(massageWithPicConfig.getProperty(String.class,"massageWithImageAddress")));
        try {
            if (massage.getImageId() != null) {
                pane = loader2.load();
                MassageWithImageView view1 = loader2.getController();
                ImageController controller = new ImageController();
                view1.getImageView().setImage(controller.getImage(massage.getImageId()));
                view = view1;
            } else {
                pane = loader1.load();
                view = loader1.getController();
            }
            view.getMassageLabel().setText(massage.getMassage());
            view.getUsernameLabel().setText(sender.getUsername());
            if (sender.getProfileImageId() != null) {
                ImageController controller = new ImageController();
                view.getCircle().setFill(new ImagePattern(controller.getImage(sender.getProfileImageId())));
            }
            if (type.equals(massageWithPicConfig.getProperty("noDelete"))) {
                view.getDeleteBtn().setVisible(false);
                view.getEditBtn().setVisible(false);
            } else {
                view.getDeleteBtn().setVisible(true);
                view.getEditBtn().setVisible(true);
            }
            view.getSaveBtn().setVisible(true);
            logger.info(massageWithPicConfig.getProperty(String.class,"massageWithPicInfo"));
        } catch (Exception e) {
            logger.warn(massageWithPicConfig.getProperty(String.class,"massageWithPicWarn"));
            e.printStackTrace();
        }
    }

    public AnchorPane getPane() {
        return pane;
    }

    public MassageWithUserPicView getView() {
        return view;
    }
}