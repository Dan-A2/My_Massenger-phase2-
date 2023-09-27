package org.openjfx.view.watchUserPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.ImagePattern;
import org.openjfx.SceneManager;
import org.openjfx.controller.Config;
import org.openjfx.controller.modelController.ImageController;
import org.openjfx.event.BlockEvent;
import org.openjfx.event.FollowRequestEvent;
import org.openjfx.event.Follow_UnfollowEvent;
import org.openjfx.event.Mute_UnmuteEvent;
import org.openjfx.listeners.blocking.Block_UnblockListenerImplementation;
import org.openjfx.listeners.users.FollowRequestListenerImplementation;
import org.openjfx.listeners.users.Follow_UnfollowUnfollowListenerImplementation;
import org.openjfx.listeners.users.Mute_UnMuteListenerImplementation;
import org.openjfx.listeners.CommandListener;
import org.openjfx.models.User;
import org.openjfx.view.COMMANDS;
import org.openjfx.view.chat.ChatFX;
import org.openjfx.view.tweet.TweetPage;
import org.openjfx.view.lists.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WatchUserPage {

    static private final Logger logger = (Logger) LogManager.getLogger(WatchUserPage.class);
    private User showFrom;
    private User showTo;

    public WatchUserPage(User showFrom, User showTo) {
        this.showFrom = showFrom;
        this.showTo = showTo;
    }

    public void show(){

        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(Config.getConfig("watchUserPage").getProperty(String.class,"address")));
        try {
            Parent root = loader.load();
            WatchUserPageView view = loader.getController();
            view.getFirstnameLabel().setText(showFrom.getFirstName());
            view.getLastnameLabel().setText(showFrom.getLastName());
            if (showFrom.getLastSeen() != null) {
                if (showFrom.getWhoCanSeeLastSeen().equals(Config.getConfig("setting").getProperty(String.class, "nobody"))) {
                    view.getLastSeenLabel().setText("...");
                } else if (showFrom.getWhoCanSeeLastSeen().equals(Config.getConfig("setting").getProperty(String.class, "followers"))) {
                    if (showFrom.getFollowersId().contains(showTo.getId())) {
                        view.getLastSeenLabel().setText(showFrom.getLastSeen());
                        if (showFrom.getProfileImageId() != null) {
                            ImageController controller = new ImageController();
                            view.getProfileCircle().setFill(new ImagePattern(controller.getImage(showFrom.getProfileImageId())));
                        }
                    } else {
                        view.getLastSeenLabel().setText("...");
                    }
                } else {
                    view.getLastSeenLabel().setText(showFrom.getLastSeen());
                    if (showFrom.getProfileImageId() != null) {
                        ImageController controller = new ImageController();
                        view.getProfileCircle().setFill(new ImagePattern(controller.getImage(showFrom.getProfileImageId())));
                    }
                }
            }
            view.getUsernameLabel().setText(showFrom.getUsername());
            view.getFollowersNum().setText(Integer.toString(showFrom.getFollowersId().size()));
            view.getFollowingsNum().setText(Integer.toString(showFrom.getFollowingsId().size()));
            view.getTweetsNum().setText(Integer.toString(showFrom.getMyTweetsId().size()));
            update(view);
            setListener(view);
            SceneManager.getSceneManager().push(new Scene(root));
            logger.info(Config.getConfig("watchUserPage").getProperty(String.class, "info"));
        } catch (Exception e) {
            logger.warn(Config.getConfig("watchUserPage").getProperty(String.class,"warn"));
            e.printStackTrace();
        }

    }

    private void update(WatchUserPageView view){

        if (showTo.getFollowingsId().contains(showFrom.getId())){
            view.getFollowBtn1().setVisible(false);
            view.getUnfollowBtn1().setVisible(true);
        } else if (showFrom.getRequestersId().contains(showTo.getId())) {
            view.getRequestedBtn().setVisible(true);
            view.getFollowBtn1().setVisible(false);
            view.getUnfollowBtn1().setVisible(false);
        } else {
            view.getFollowBtn1().setVisible(true);
            view.getUnfollowBtn1().setVisible(false);
        }
        if ((showTo.getFollowingsId().contains(showFrom.getId()) || showFrom.getFollowingsId().contains(showTo.getId())) && !showTo.getBlackListId().contains(showFrom.getId()) && !showFrom.getBlackListId().contains(showTo.getId())) {
            view.getSendMassageBtn().setVisible(true);
        } else {
            view.getSendMassageBtn().setVisible(false);
        }
        if (showTo.getBlackListId().contains(showFrom.getId())) {
            view.getBlockBtn().setVisible(false);
            view.getUnblockBtn().setVisible(true);
        } else {
            view.getBlockBtn().setVisible(true);
            view.getUnblockBtn().setVisible(false);
        }
        if(showTo.getMutedId().contains(showFrom.getId())) {
            view.getMuteBtn().setVisible(false);
            view.getUnmuteBtn().setVisible(true);
        } else {
            view.getMuteBtn().setVisible(true);
            view.getUnmuteBtn().setVisible(false);
        }

    }

    private void setListener(WatchUserPageView view){

        view.setListener(new CommandListener() {
            @Override
            public void listenCommand(COMMANDS command) {
                if (command == COMMANDS.BLOCK) {
                    BlockEvent event = new BlockEvent(showTo, showFrom.getId());
                    Block_UnblockListenerImplementation implementation = new Block_UnblockListenerImplementation();
                    implementation.listenBlock(event);
                } else if (command == COMMANDS.FOLLOW) {
                    if (showFrom.isAccountPublic()) {
                        Follow_UnfollowEvent event1 = new Follow_UnfollowEvent(showTo, showFrom);
                        Follow_UnfollowUnfollowListenerImplementation implementation1 = new Follow_UnfollowUnfollowListenerImplementation();
                        implementation1.listenFollow(event1);
                    } else {
                        FollowRequestEvent event = new FollowRequestEvent(showFrom, showTo);
                        FollowRequestListenerImplementation implementation = new FollowRequestListenerImplementation();
                        implementation.listenAddRequest(event);
                        view.getFollowBtn1().setVisible(false);
                        view.getRequestedBtn().setVisible(true);
                    }
                } else if (command == COMMANDS.REMOVEREQUEST) {
                    FollowRequestEvent event = new FollowRequestEvent(showFrom, showTo);
                    FollowRequestListenerImplementation implementation = new FollowRequestListenerImplementation();
                    implementation.listenRemoveRequest(event);
                    view.getRequestedBtn().setVisible(false);
                    view.getFollowBtn1().setVisible(true);
                } else if (command == COMMANDS.BACK) {
                    SceneManager.getSceneManager().getBack();
                } else if (command == COMMANDS.MAINMENU) {
                    SceneManager.getSceneManager().backToMain();
                } else if (command == COMMANDS.MOUSECHANGEFOLLOWP) {
                    view.getFollowBtn1().setVisible(false);
                    view.getFollowBtn2().setVisible(true);
                } else if (command == COMMANDS.MOUSECHANGEFOLLOWN) {
                    view.getFollowBtn1().setVisible(true);
                    view.getFollowBtn2().setVisible(false);
                } else if (command == COMMANDS.MOUSECHANGEUNFOLLOWP) {
                    view.getUnfollowBtn1().setVisible(false);
                    view.getUnfollowBtn2().setVisible(true);
                } else if (command == COMMANDS.MOUSECHANGEUNFOLLOWN) {
                    view.getUnfollowBtn1().setVisible(true);
                    view.getUnfollowBtn2().setVisible(false);
                } else if (command == COMMANDS.MUTE) {
                    Mute_UnmuteEvent event2 = new Mute_UnmuteEvent(showTo, showFrom.getId());
                    Mute_UnMuteListenerImplementation implementation2 = new Mute_UnMuteListenerImplementation();
                    implementation2.listenMuter(event2);
                } else if (command == COMMANDS.SHOWFOLLOWERS) {
                    if (showFrom.isAccountPublic() || (!showFrom.isAccountPublic() && showFrom.getFollowersId().contains(showTo.getId()))) {
                        Lists lists = new Lists();
                        lists.showUsersOfSorting(showTo, showFrom.getFollowersId(), "Followers");
                    }
                } else if (command == COMMANDS.SHOWFOLLOWINGS) {
                    if (showFrom.isAccountPublic() || (!showFrom.isAccountPublic() && showFrom.getFollowersId().contains(showTo.getId()))) {
                        Lists lists = new Lists();
                        lists.showUsersOfSorting(showTo, showFrom.getFollowingsId(), "Followings");
                    }
                } else if (command == COMMANDS.SHOWTWEETS) {
                    if (showFrom.isAccountPublic() || (!showFrom.isAccountPublic() && showFrom.getFollowersId().contains(showTo.getId()))) {
                        TweetPage tweetPage = new TweetPage();
                        tweetPage.showMyTweets(showFrom);
                    }
                } else if (command == COMMANDS.UNBLOCK) {
                    BlockEvent event3 = new BlockEvent(showTo, showFrom.getId());
                    Block_UnblockListenerImplementation implementation3 = new Block_UnblockListenerImplementation();
                    implementation3.listenUnblock(event3);
                } else if (command == COMMANDS.UNFOLLOW) {
                    Follow_UnfollowEvent event4 = new Follow_UnfollowEvent(showTo, showFrom);
                    Follow_UnfollowUnfollowListenerImplementation implementation4 = new Follow_UnfollowUnfollowListenerImplementation();
                    implementation4.listenUnfollow(event4);
                } else if (command == COMMANDS.UNMUTE) {
                    Mute_UnmuteEvent event5 = new Mute_UnmuteEvent(showTo, showFrom.getId());
                    Mute_UnMuteListenerImplementation implementation5 = new Mute_UnMuteListenerImplementation();
                    implementation5.listenUnMuter(event5);
                } else if (command == COMMANDS.SENDMASSAGE) {
                    ChatFX chat = new ChatFX();
                    chat.generateChat(showTo, showFrom);
                }
                update(view);
            }
        });

    }

}