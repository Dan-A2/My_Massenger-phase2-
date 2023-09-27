package org.openjfx.view.tweet.forward;

import org.openjfx.controller.Config;
import org.openjfx.models.User;
import org.openjfx.view.lists.Lists;

import javax.swing.*;

public class Forward {

    private User forwardFrom;
    private String tweetText;
    private int imageId;

    public Forward(User forwardFrom, String tweetText, int imageId) {
        this.forwardFrom = forwardFrom;
        this.tweetText = tweetText;
        this.imageId = imageId;
    }

    public void show(){
        Config config = Config.getConfig("tweet");
        String[] options = {config.getProperty(String.class,"forwardOption1"), config.getProperty(String.class,"forwardOption2")};
        int ans = JOptionPane.showOptionDialog(null, config.getProperty(String.class,"forwardMassage"),
                config.getProperty(String.class,"forwardTitle"),
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        Lists list = new Lists();
        list.setMassageToBeForwarded(tweetText);
        list.setImageId(imageId);
        if(ans == 0) {
            list.show(forwardFrom, config.getProperty(String.class,"forwardT"));
        } else if(ans == 1) {
            list.show(forwardFrom, config.getProperty(String.class,"forwardSortingT"));
        }
    }

    public User getForwardFrom() {
        return forwardFrom;
    }

    public String getTweetText() {
        return tweetText;
    }
}