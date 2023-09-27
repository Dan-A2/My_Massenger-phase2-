package org.openjfx.view.lists.chat;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.ChatController;
import org.openjfx.event.ClearUnseenChatEvent;
import org.openjfx.listeners.CommandListener;
import org.openjfx.listeners.massages.UpdateUnseenMassagesListenerImplementation;
import org.openjfx.models.Chat;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.chat.ChatFX;
import org.openjfx.view.mainMenu.Mainmenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatForList {

    static private final Logger logger = (Logger) LogManager.getLogger(ChatForList.class);
    private AnchorPane pane;
    private Chat chat;
    private int unseen;

    public ChatForList(Chat chat, int unseen) {
        this.chat = chat;
        this.unseen = unseen;
    }

    public void show() {
        Config config = Config.getConfig("lists");
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(config.getProperty(String.class, "chatForListAddress")));
        try {
            pane = loader.load();
            ChatForListView view = loader.getController();
            ChatController controller = new ChatController();
            view.getChatLabel().setText(controller.findTheOtherUser(chat, Mainmenu.getMenuView().getCurrentUser()).getUsername());
            view.getUnseenChatsLabel().setText(Integer.toString(unseen));
            view.setChatListListener(new CommandListener() {
                @Override
                public void listenCommand(COMMANDS command) {
                    if (command == COMMANDS.VIEW) {
                        ChatFX chat1 = new ChatFX();
                        ClearUnseenChatEvent event = new ClearUnseenChatEvent(chat, Mainmenu.getMenuView().getCurrentUser());
                        UpdateUnseenMassagesListenerImplementation implementation = new UpdateUnseenMassagesListenerImplementation();
                        implementation.clearChat(event);
                        chat1.generateChat(Mainmenu.getMenuView().getCurrentUser(), controller.findTheOtherUser(chat, Mainmenu.getMenuView().getCurrentUser()));
                    }
                }
            });
            logger.info(config.getProperty(String.class,"chatInfo"));
        } catch (Exception e) {
            logger.warn(config.getProperty(String.class,"chatWarn"));
            e.printStackTrace();
        }
    }

    public AnchorPane getPane() {
        return pane;
    }

}