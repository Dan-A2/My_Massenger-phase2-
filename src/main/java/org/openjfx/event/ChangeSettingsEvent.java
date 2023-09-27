package org.openjfx.event;

import org.openjfx.models.User;

public class ChangeSettingsEvent {

    private User user;
    private boolean accountPublicity;
    private String whoCanSeeLastSeen;
    private boolean accountActivity;

    public ChangeSettingsEvent(User user, boolean accountPublicity, String whoCanSeeLastSeen, boolean accountActivity) {
        this.user = user;
        this.accountPublicity = accountPublicity;
        this.whoCanSeeLastSeen = whoCanSeeLastSeen;
        this.accountActivity = accountActivity;
    }

    public boolean isAccountPublicity() {
        return accountPublicity;
    }

    public String getWhoCanSeeLastSeen() {
        return whoCanSeeLastSeen;
    }

    public boolean isAccountActivity() {
        return accountActivity;
    }

    public User getUser() {
        return user;
    }
}