package org.openjfx.view.yourAccount;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.ImageController;
import org.openjfx.event.TweetEvent;
import org.openjfx.listeners.tweetNComment.AddTweetListenerImplementation;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.mainMenu.Mainmenu;
import org.openjfx.view.tweet.TweetPage;
import org.openjfx.view.lists.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class YourAccount {

    static private final Logger logger = (Logger) LogManager.getLogger(YourAccount.class);
    private FXMLLoader loader;
    private User user;
    private AnchorPane pane;
    private File data;

    public YourAccount(User user) {
        this.user = user;
    }

    public void create(){

        Config config = Config.getConfig("yourAccount");
        Config config1 = Config.getConfig("lists");
        loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"yourAccountAddress")));
        try {
            pane = loader.load();
            YourAccountView currentView = loader.getController();
            currentView.setYourAccountListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if(command == COMMANDS.POSTTWEET) {
                        postTweet(currentView);
                    } else if(command == COMMANDS.SHOWBLOCKED) {
                        Lists lists = new Lists();
                        lists.show(user, config1.getProperty(String.class,"blocked"));
                    } else if(command == COMMANDS.SHOWFOLLOWERS) {
                        Lists lists = new Lists();
                        lists.show(user, config1.getProperty(String.class,"followers"));
                    } else if(command == COMMANDS.SHOWFOLLOWINGS) {
                        Lists lists = new Lists();
                        lists.show(user, config1.getProperty(String.class,"followings"));
                    } else if(command == COMMANDS.SHOWSORTINGS) {
                        Lists lists = new Lists();
                        lists.show(user, config1.getProperty(String.class,"sorting"));
                    } else if(command == COMMANDS.SHOWMYTWEETS) {
                        TweetPage tweetPage = new TweetPage();
                        tweetPage.showMyTweets(Mainmenu.getMenuView().getCurrentUser());
                    } else if(command == COMMANDS.GOTOPROFILE) {
                        Profile profile = new Profile(user);
                        profile.show();
                    } else if (command == COMMANDS.SHOWNOTIFS) {
                        Lists lists = new Lists();
                        lists.show(user, config1.getProperty(String.class,"notif"));
                    } else if (command == COMMANDS.SHOWREQUESTERS) {
                        Lists lists = new Lists();
                        lists.show(user, config1.getProperty(String.class, "requester"));
                    } else if (command == COMMANDS.SELECTIMAGE) {
                        FileChooser fileChooser = new FileChooser();
                        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(config.getProperty("imageType"), "*.png");
                        fileChooser.getExtensionFilters().addAll(extFilterPNG);
                        try {
                            data = fileChooser.showOpenDialog(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            logger.info(config.getProperty(String.class,"yourAccountInfo"));
        } catch (IOException e) {
            logger.warn(config.getProperty(String.class,"yourAccountError"));
            e.printStackTrace();
        }

    }

    private void postTweet(YourAccountView currentView) {
        if(!currentView.getTweetArea().getText().equals("")) {
            JOptionPane.showMessageDialog(null, Config.getConfig("yourAccount").getProperty(String.class,"yourAccountMassage1"));
        } else {
            JOptionPane.showMessageDialog(null, Config.getConfig("yourAccount").getProperty(String.class,"yourAccountMassage2"));
        }
        currentView.getTweetArea().setVisible(false);
        currentView.getPostBtn().setVisible(false);
        currentView.getAttachBtn().setVisible(false);
        TweetEvent event = null;
        if (data != null) {
            ImageController controller = new ImageController();
            try {
                BufferedImage bufferedImage = ImageIO.read(data);
                int id = controller.saveImage(SwingFXUtils.toFXImage(bufferedImage, null));
                event = new TweetEvent(currentView.getTweetArea().getText(), user.getId(), id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            event = new TweetEvent(currentView.getTweetArea().getText(), user.getId(), -2);
        }
        AddTweetListenerImplementation tweetListenerImplementation = new AddTweetListenerImplementation();
        tweetListenerImplementation.listen(event);
    }

    public AnchorPane getPane() {
        return pane;
    }
}