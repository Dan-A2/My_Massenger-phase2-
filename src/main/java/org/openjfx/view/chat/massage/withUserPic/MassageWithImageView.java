package org.openjfx.view.chat.massage.withUserPic;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class MassageWithImageView extends MassageWithUserPicView{

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
    private ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }
}