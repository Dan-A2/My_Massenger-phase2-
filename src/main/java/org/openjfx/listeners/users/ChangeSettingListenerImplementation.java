package org.openjfx.listeners.users;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.ChangeSettingsEvent;

public class ChangeSettingListenerImplementation implements ChangeSettingListener{

    private UserController controller = new UserController();

    @Override
    public void change(ChangeSettingsEvent event) {
        controller.changeSetting(event.getUser(), event.isAccountPublicity(), event.getWhoCanSeeLastSeen(), event.isAccountActivity());
    }
}