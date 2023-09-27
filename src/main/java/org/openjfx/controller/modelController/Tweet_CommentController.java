package org.openjfx.controller.modelController;

import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.models.Tweet_Comment;
import org.openjfx.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class Tweet_CommentController {

    static private final Logger logger = (Logger) LogManager.getLogger(Tweet_CommentController.class);

    public void like(int tweetId){ // notification handled in user controller
        Tweet_Comment tweet = find(tweetId);
        tweet.setLikes(tweet.getLikes()+1);
        SaveNLoad.saveTweet_Comments();
    }

    public void dislike(int tweetId){ // notification handled in user controller
        Tweet_Comment tweet = find(tweetId);
        tweet.setLikes(tweet.getLikes()-1);
        if(tweet.getLikes() < 0){
            tweet.setLikes(0);
        }
        SaveNLoad.saveTweet_Comments();
    }

    public User getSender(Tweet_Comment tweet) {
        for (User user: User.getActiveUsers()) {
            if(user.getId() == tweet.getSenderID() && user.isActive()){
                return user;
            }
        }
        return null;
    }

    public LinkedList<Tweet_Comment> getComments(Tweet_Comment tweet) {
        LinkedList<Tweet_Comment> comments = new LinkedList<>();
        for (Integer commentId : tweet.getCommentsId()){
            for (Tweet_Comment comment : Tweet_Comment.getAll()) {
                if (comment.getID() == commentId){
                    comments.add(comment);
                }
            }
        }
        return comments;
    }

    public Tweet_Comment find(int id){
        for (Tweet_Comment tweet : Tweet_Comment.getAll()) {
            if(tweet.getID() == id){
                return tweet;
            }
        }
        return null;
    }

    public void report(Tweet_Comment tweet){
        tweet.setReported(tweet.getReported()+1);
        logger.info("Tweet " + tweet.getID() + " is reported");
        SaveNLoad.saveTweet_Comments();
        if (tweet.getReported() > 10) {
            logger.info("Tweet " + tweet.getID() + " is deleted because of reports");
            deleteTweet(tweet);
        }
    }

    public void deleteTweet(Tweet_Comment tweet) {
        getSender(tweet).getMyTweetsId().remove(new Integer(tweet.getID()));
        for (User user : User.getActiveUsers()) {
            user.getSavedTweetsId().remove(new Integer(tweet.getID()));
        }
        for (User user : User.getInactiveUsers()) {
            user.getSavedTweetsId().remove(new Integer(tweet.getID()));
        }
        logger.info("Tweet " + tweet.getID() + " is deleted");
        for (int i = 0; i < Tweet_Comment.getAll().size(); i++) {
            if (Tweet_Comment.getAll().get(i) == tweet) {
                for (int j = 0; j < Tweet_Comment.getAll().get(i).getCommentsId().size(); j++) {
                    int id = Tweet_Comment.getAll().get(i).getCommentsId().get(j);
                    Tweet_Comment comment = find(id);
                    deleteTweet(comment);
                }
                Tweet_Comment.getAll().remove(tweet);
                i--;
            }
        }
        SaveNLoad.saveUsers();
        SaveNLoad.saveTweet_Comments();
    }

    public void sortByLike(LinkedList<Tweet_Comment> tweets){

        for (int i = 0; i < tweets.size()-1; i++) {
            for (int j = i+1; j < tweets.size(); j++) {
                if(tweets.get(i).getLikes() < tweets.get(j).getLikes()){
                    Tweet_Comment arts = tweets.get(i);
                    tweets.set(i, tweets.get(j));
                    tweets.set(j, arts);
                    i = -1;
                    break;
                }
            }
        }

    }

}