package org.openjfx.view.massaging;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.SaveNoteEvent;
import org.openjfx.listeners.CommandListener;
import org.openjfx.listeners.massages.AddNoteListenerImplementation;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.lists.ListSelectSortingGroup;
import org.openjfx.view.lists.Lists;
import org.openjfx.view.tweet.TweetPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class Massaging {

    static private final Logger logger = (Logger) LogManager.getLogger(Massaging.class);
    private User user;
    private AnchorPane pane;

    public Massaging(User user) {
        this.user = user;
    }

    public void generate(){
        Config config = Config.getConfig("massaging");
        Config config1 = Config.getConfig("lists");
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class,"address")));
        try {
            pane = loader.load();
            MassagingView view = loader.getController();
            view.setMassagingListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if (command == COMMANDS.GOTOCHATS) {
                        Lists lists = new Lists();
                        lists.show(user, config1.getProperty(String.class,"chats"));
                    } else if (command == COMMANDS.CREATESORTING) {
                        ListSelectSortingGroup listSelectSortingGroup = new ListSelectSortingGroup();
                        listSelectSortingGroup.generate(user, true);
                    } else if (command == COMMANDS.GOTONOTES) {
                        TweetPage tweetPage = new TweetPage();
                        tweetPage.showSavedNotes(user);
                    } else if (command == COMMANDS.GOTOSAVEDMASSAGES) {
                        TweetPage tweetPage = new TweetPage();
                        tweetPage.showSavedMassages(user);
                    } else if (command == COMMANDS.GOTOSAVEDTWEETS) {
                        TweetPage tweetPage = new TweetPage();
                        UserController controller = new UserController();
                        tweetPage.showInGeneral(controller.getSavedTweets(user));
                    } else if (command == COMMANDS.SENDMASSAGETOSORTING) {
                        String massage = JOptionPane.showInputDialog(config.getProperty(String.class,"inputDialog"));
                        if (massage != null && !massage.equals("")) {
                            Lists lists = new Lists();
                            lists.setMassageToBeForwarded(massage);
                            lists.show(user, config1.getProperty(String.class,"sendSorting"));
                        }
                    } else if (command == COMMANDS.SHOWGROUPS) {
                        Lists lists = new Lists();
                        lists.show(user, config1.getProperty(String.class, "groupShow"));
                    } else if (command == COMMANDS.CREATEGROUP) {
                        ListSelectSortingGroup listSelectSortingGroup = new ListSelectSortingGroup();
                        listSelectSortingGroup.generate(user, false);
                    } else if (command == COMMANDS.ADDNOTE) {
                        String note = JOptionPane.showInputDialog("enter the note");
                        if (note != null && !note.equals("")) {
                            SaveNoteEvent event = new SaveNoteEvent(user, note);
                            AddNoteListenerImplementation implementation = new AddNoteListenerImplementation();
                            implementation.listen(event);
                        }
                    }
                }
            });
            logger.info(config.getProperty(String.class, "info"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"warn"));
            e.printStackTrace();
        }
    }

    public AnchorPane getPane() {
        return pane;
    }
}