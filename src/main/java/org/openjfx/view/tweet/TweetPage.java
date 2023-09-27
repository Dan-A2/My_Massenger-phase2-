package org.openjfx.view.tweet;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.MassageController;
import org.openjfx.controller.modelController.Tweet_CommentController;
import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.DeleteMassageEvent;
import org.openjfx.listeners.CommandListener;
import org.openjfx.listeners.massages.EditDeleteMassageListenerImplementation;
import org.openjfx.models.Massage;
import org.openjfx.models.Tweet_Comment;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.chat.massage.MassageFX;
import org.openjfx.view.chat.massage.withUserPic.MassageWithUserPic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjfx.view.mainMenu.Mainmenu;

import java.util.LinkedList;

public class TweetPage {

    static private final Logger logger = (Logger) LogManager.getLogger(TweetPage.class);
    private Config config = Config.getConfig("tweet");

    public void showMyTweets(User user) {

        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"tweetsAddress")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            TweetPageView currentView = loader.getController();
            setListener(currentView);
            Tweet_CommentController controller = new Tweet_CommentController();
            for (Integer id : user.getMyTweetsId()) {
                TweetComponent tweetComponent = new TweetComponent();
                tweetComponent.generate(controller.find(id));
                currentView.getGridPane().add(tweetComponent.getPane(), 0, currentView.getGridPane().getRowCount()+1);
                currentView.getGridPane().setMargin(tweetComponent.getPane(), new Insets(10));
            }
            SceneManager.getSceneManager().push(scene);
            logger.info(config.getProperty(String.class,"tweetsInfo"));
        } catch (Exception e){
            logger.warn(config.getProperty(String.class,"tweetsWarn"));
            e.printStackTrace();
        }
    }

    public void showInGeneral(LinkedList<Tweet_Comment> tweets){

        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"tweetsAddress")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            TweetPageView currentView = loader.getController();
            setListener(currentView);
            for (Tweet_Comment tweet_comment : tweets) {
                TweetComponent tweetComponent = new TweetComponent();
                tweetComponent.generate(tweet_comment);
                currentView.getGridPane().add(tweetComponent.getPane(), 0, currentView.getGridPane().getRowCount()+1);
                currentView.getGridPane().setMargin(tweetComponent.getPane(), new Insets(10));
            }
            SceneManager.getSceneManager().push(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showSavedNotes(User user){

        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"tweetsAddress")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            TweetPageView currentView = loader.getController();
            setListener(currentView);
            UserController controller = new UserController();
            for (Massage massage : controller.getSavedTextsOfMe(user)) {
                MassageFX massageFX = new MassageFX(massage.getMassage());
                massageFX.setListener(new CommandListener() {
                    @Override
                    public void listenCommand(COMMANDS commands) {
                        if (commands == COMMANDS.DELETE) {
                            DeleteMassageEvent event = new DeleteMassageEvent(massage, Mainmenu.getMenuView().getCurrentUser());
                            EditDeleteMassageListenerImplementation implementation = new EditDeleteMassageListenerImplementation();
                            implementation.listenDeleteTotally(event);
                            SceneManager.getSceneManager().pop();
                            showSavedNotes(user);
                        }
                    }
                });
                currentView.getGridPane().add(massageFX.getMassagePane(), 0, currentView.getGridPane().getRowCount()+1);
                currentView.getGridPane().setMargin(massageFX.getMassagePane(), new Insets(10));
            }
            SceneManager.getSceneManager().push(scene);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void showSavedMassages(User user){
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"tweetsAddress")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            TweetPageView currentView = loader.getController();
            setListener(currentView);
            UserController controller = new UserController();
            MassageController controller1 = new MassageController();
            for (Massage massage : controller.getSavedMassages(user)) {
                MassageWithUserPic massage1 = new MassageWithUserPic(controller1.getSender(massage), massage);
                massage1.create(Config.getConfig("chat").getProperty(String.class, "Deletable"));
                massage1.getView().setListener(new CommandListener() {
                    @Override
                    public void listenCommand(COMMANDS commands) {
                        if (commands == COMMANDS.DELETE) {
                            DeleteMassageEvent event = new DeleteMassageEvent(massage, user);
                            EditDeleteMassageListenerImplementation implementation = new EditDeleteMassageListenerImplementation();
                            implementation.listenDeleteForUser(event);
                            showSavedMassages(user);
                        }
                    }
                });
                massage1.getView().getSaveBtn().setVisible(false);
                massage1.getView().getEditBtn().setVisible(false);
                currentView.getGridPane().add(massage1.getPane(), 0, currentView.getGridPane().getRowCount()+1);
                currentView.getGridPane().setMargin(massage1.getPane(), new Insets(10));
            }
            SceneManager.getSceneManager().push(scene);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setListener(TweetPageView view) {
        view.setListener(new CommandListener() {
            @Override
            public void listenCommand(COMMANDS command) {
                if(command == COMMANDS.BACK){
                    SceneManager.getSceneManager().getBack();
                } else if (command == COMMANDS.MAINMENU) {
                    SceneManager.getSceneManager().backToMain();
                }
            }
        });
    }

}