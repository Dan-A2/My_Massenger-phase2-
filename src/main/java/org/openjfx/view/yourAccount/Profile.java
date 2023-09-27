package org.openjfx.view.yourAccount;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.ImageController;
import org.openjfx.event.ProfileEditEvent;
import org.openjfx.listeners.users.ProfileEditListenerImplementation;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.User;
import org.openjfx.validation.CheckValidity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.view.COMMANDS;

import javax.imageio.ImageIO;

public class Profile {

    static private final Logger logger = (Logger) LogManager.getLogger(Profile.class);
    private User user;
    private ProfileView currentView;
    private static FXMLLoader profileLoader;
    private boolean onEditMode;
    private File data;

    public Profile(User user) {
        this.user = user;
    }

    public void show(){

        Config config = Config.getConfig("yourAccount");
        profileLoader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"profileAddress")));
        try {
            Parent root = profileLoader.load();
            Scene scene = new Scene(root);
            SceneManager.getSceneManager().push(scene);
            currentView = profileLoader.getController();
            update();
            currentView.setProfileListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if(command == COMMANDS.EDIT) {
                        setEditables(true);
                        currentView.getEditBtn().setVisible(false);
                        currentView.getSaveBtn().setVisible(true);
                    } else if(command == COMMANDS.BACK) {
                        SceneManager.getSceneManager().getBack();
                    } else if (command == COMMANDS.SELECTIMAGE && onEditMode) {
                        FileChooser fileChooser = new FileChooser();
                        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(config.getProperty("imageType"), "*.png");
                        fileChooser.getExtensionFilters().addAll(extFilterPNG);
                        try {
                            data = fileChooser.showOpenDialog(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.warn("an error occurred while trying to load image.");
                        }
                    } else if (command == COMMANDS.SAVE) {
                        String firstname = currentView.getFirstnameField().getText();
                        String lastname = currentView.getLastnameField().getText();
                        String username = currentView.getUsernameField().getText();
                        String password = currentView.getPasswordField().getText();
                        String email = currentView.getEmailField().getText();
                        String bio = currentView.getBioArea().getText();
                        String phoneNumber = currentView.getPhonenumberField().getText();
                        if ((firstname.equals("") || lastname.equals("") || username.equals("") || password.equals("") || email.equals(""))) {
                            currentView.getErrorLabel().setText(config.getProperty(String.class, "emptyError"));
                        } else if (!CheckValidity.isNameValid(firstname) || !CheckValidity.isNameValid(lastname)) {
                            currentView.getErrorLabel().setText(config.getProperty(String.class, "numberError"));
                        } else if (!CheckValidity.isUsernameValid(username) || (CheckValidity.isUsernameRepeated(username) && !user.getUsername().equals(username))) {
                            currentView.getErrorLabel().setText(config.getProperty(String.class, "usernameError"));
                        } else if (CheckValidity.isEmailRepeated(email) && !user.getEmail().equals(email)) {
                            currentView.getErrorLabel().setText(config.getProperty(String.class, "emailError"));
                        } else if (!phoneNumber.equals("") && (!CheckValidity.isNumberValid(phoneNumber) || (CheckValidity.isPhoneNumberRepeated(phoneNumber) && !user.getPhoneNumber().equals(phoneNumber)))) {
                            currentView.getErrorLabel().setText(config.getProperty(String.class, "phonenumberError"));
                        } else {
                            ProfileEditEvent event = new ProfileEditEvent(user, firstname, lastname, username,
                                    password, email, bio, phoneNumber,
                                    currentView.getBirthdayPicker().getValue());
                            ProfileEditListenerImplementation implementation = new ProfileEditListenerImplementation();
                            if (data != null) {
                                ImageController controller = new ImageController();
                                try {
                                    BufferedImage bufferedImage = ImageIO.read(data);
                                    int id = controller.saveImage(SwingFXUtils.toFXImage(bufferedImage, null));
                                    user.setProfileImageId(id);
                                    currentView.getProfileCircle().setFill(new ImagePattern(SwingFXUtils.toFXImage(bufferedImage, null)));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            implementation.listen(event);
                            setEditables(false);
                            currentView.getEditBtn().setVisible(true);
                            currentView.getSaveBtn().setVisible(false);
                            update();
                        }
                    }
                }
            });
            logger.info(config.getProperty(String.class,"profileInfo"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"profileError"));
            e.printStackTrace();
        }

    }

    public void update(){
        currentView.getFirstnameField().setText(user.getFirstName());
        currentView.getLastnameField().setText(user.getLastName());
        currentView.getUsernameField().setText(user.getUsername());
        currentView.getEmailField().setText(user.getEmail());
        currentView.getPasswordField().setText(user.getPassword());
        if (user.getProfileImageId() != null) {
            ImageController controller = new ImageController();
            Image image = controller.getImage(user.getProfileImageId());
            currentView.getProfileCircle().setFill(new ImagePattern(image));
        }
        if (user.getBirthday() != null) {
            currentView.getBirthdayLabel().setText(user.getBirthday());
            currentView.getBirthdayLabel().setVisible(true);
        }
        if (user.getBio() != null) {
            currentView.getBioArea().setText(user.getBio());
        }
        if (user.getPhoneNumber() != null) {
            currentView.getPhonenumberField().setText(user.getPhoneNumber());
        }
    }

    public FXMLLoader getProfileLoader() {
        return profileLoader;
    }

    private void setEditables(boolean b){
        currentView.getFirstnameField().setEditable(b);
        currentView.getLastnameField().setEditable(b);
        currentView.getUsernameField().setEditable(b);
        currentView.getEmailField().setEditable(b);
        currentView.getPhonenumberField().setEditable(b);
        currentView.getPasswordField().setEditable(b);
        currentView.getBirthdayPicker().setEditable(b);
        currentView.getBioArea().setEditable(b);
        currentView.getSetProfBtn().setVisible(b);
        currentView.getBirthdayLabel().setVisible(!b);
        currentView.getBirthdayPicker().setVisible(b);
        onEditMode = b;
    }
}