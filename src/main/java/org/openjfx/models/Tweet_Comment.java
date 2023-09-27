package org.openjfx.models;

import com.google.gson.annotations.Expose;
import org.openjfx.controller.saveLoad.SaveNLoad;

import java.util.LinkedList;

public class Tweet_Comment {

    private int likes;

    private String text;
    private Integer senderID;
    private int ID;
    private int reported;
    private Integer imageId;
    private static int lastId = 0;
    private LinkedList<Integer> commentsId;
    @Expose(serialize = false, deserialize = false)
    static LinkedList<Tweet_Comment> all = new LinkedList<>();

    public Tweet_Comment(String text, Integer senderId) {
        this.likes = 0;
        this.reported = 0;
        this.text = text;
        this.commentsId = new LinkedList<>();
        lastId++;
        this.ID = lastId;
        this.senderID = senderId;
        this.commentsId = new LinkedList<>();
        all.add(this);
        SaveNLoad.saveTweet_Comments();
    }

    public String getText() {
        return text;
    }

    public LinkedList<Integer> getCommentsId() {
        return commentsId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Integer getSenderID() {
        return senderID;
    }

    public void setSenderID(Integer senderID) {
        this.senderID = senderID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getReported() {
        return reported;
    }

    public void setReported(int reported) {
        this.reported = reported;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        Tweet_Comment.lastId = lastId;
    }

    public void setCommentsId(LinkedList<Integer> commentsId) {
        this.commentsId = commentsId;
    }

    public static LinkedList<Tweet_Comment> getAll() {
        return all;
    }

    public static void setAll(LinkedList<Tweet_Comment> all) {
        Tweet_Comment.all = all;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}
