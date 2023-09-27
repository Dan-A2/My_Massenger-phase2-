package org.openjfx.models;

import com.google.gson.annotations.Expose;
import org.openjfx.controller.Config;
import org.openjfx.controller.saveLoad.SaveNLoad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;

public class User {

    static private int lastId = 0;
    @Expose(serialize = false, deserialize = false)
    static private LinkedList<User> activeUsers = new LinkedList<>();
    @Expose(serialize = false, deserialize = false)
    static private LinkedList<User> inactiveUsers = new LinkedList<>();
    private String firstName;
    private String lastName;
    private String username;
    private int id;
    private String password;
    private boolean isAccountPublic;
    private String whoCanSeeLastSeen;
    private String bio;
    private String Email;
    private String birthday;
    private String phoneNumber;
    private boolean isActive;
    private boolean isOnline;
    private String lastSeen;
    private Integer profileImageId;
    private final LinkedList<Integer> friendsId = new LinkedList<>();
    private final LinkedList<Integer> followersId = new LinkedList<>();
    private final LinkedList<Integer> followingsId = new LinkedList<>();
    private final LinkedList<Integer> blackListId = new LinkedList<>();
    private final LinkedList<Integer> mutedId = new LinkedList<>();
    private final LinkedList<Integer> unseenChatsId = new LinkedList<>();
    private final LinkedList<Integer> myChatsId = new LinkedList<>();
    private final LinkedList<Integer> savedTweetsId = new LinkedList<>();
    private final LinkedList<Integer> savedMassagesId = new LinkedList<>();
    private final LinkedList<Integer> savedTextsOfMeId = new LinkedList<>();
    private final LinkedList<Integer> myTweetsId = new LinkedList<>();
    private final LinkedList<Integer> requestersId = new LinkedList<>();
    private final LinkedList<Integer> LikedTweetsId = new LinkedList<>();
    private final LinkedList<Notification> myNotifs = new LinkedList<>();
    private final LinkedList<Integer> groupsId = new LinkedList<>();
    private final HashMap <String, LinkedList<Integer>> mySortings = new HashMap<>();
    private final HashMap<Integer, Integer> unseenMassagesGroups = new HashMap<>(); // group id -> unseen massages

    public User(String firstName, String lastName, String username, String password, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.Email = email;
        lastId++;
        this.id = lastId;
        activeUsers.add(this);
        this.setOnline(true);
        this.setAccountPublic(true);
        this.setActive(true);
        this.setWhoCanSeeLastSeen("everybody");
        SaveNLoad.saveUsers();

    }

    public static LinkedList<User> getActiveUsers() {
        return activeUsers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountPublic() {
        return isAccountPublic;
    }

    public void setAccountPublic(boolean accountPublic) {
        isAccountPublic = accountPublic;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        User.lastId = lastId;
    }

    public String getWhoCanSeeLastSeen() {
        return whoCanSeeLastSeen;
    }

    public void setWhoCanSeeLastSeen(String whoCanSeeLastSeen) {
        this.whoCanSeeLastSeen = whoCanSeeLastSeen;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Config.getConfig("yourAccount").getProperty(String.class,"dateTimeFormat2"));
        this.birthday = birthday1.format(formatter);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen() {
        LocalDateTime lastSeenMolayye = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Config.getConfig("yourAccount").getProperty(String.class,"dateTimeFormat1"));
        String lastSeen = lastSeenMolayye.format(formatter);
        this.lastSeen = lastSeen;
    }

    public HashMap<String, LinkedList<Integer>> getMySortings() {
        return mySortings;
    }

    public static LinkedList<User> getInactiveUsers() {
        return inactiveUsers;
    }

    public LinkedList<Integer> getUnseenChatsId() {
        return unseenChatsId;
    }

    public LinkedList<Integer> getMyChatsId() {
        return myChatsId;
    }

    public LinkedList<Notification> getMyNotifs() {
        return myNotifs;
    }

    public LinkedList<Integer> getSavedTweetsId() {
        return savedTweetsId;
    }

    public LinkedList<Integer> getSavedMassagesId() {
        return savedMassagesId;
    }

    public LinkedList<Integer> getSavedTextsOfMeId() {
        return savedTextsOfMeId;
    }

    public LinkedList<Integer> getMyTweetsId() {
        return myTweetsId;
    }

    public LinkedList<Integer> getRequestersId() {
        return requestersId;
    }

    public LinkedList<Integer> getLikedTweetsId() {
        return LikedTweetsId;
    }

    public LinkedList<Integer> getFriendsId() {
        return friendsId;
    }

    public LinkedList<Integer> getFollowersId() {
        return followersId;
    }

    public LinkedList<Integer> getFollowingsId() {
        return followingsId;
    }

    public LinkedList<Integer> getBlackListId() {
        return blackListId;
    }

    public LinkedList<Integer> getMutedId() {
        return mutedId;
    }

    public LinkedList<Integer> getGroupsId() {
        return groupsId;
    }

    public HashMap<Integer, Integer> getUnseenMassagesGroups() {
        return unseenMassagesGroups;
    }

    public Integer getProfileImageId() {
        return profileImageId;
    }

    public void setProfileImageId(Integer profileImageId) {
        this.profileImageId = profileImageId;
    }
}