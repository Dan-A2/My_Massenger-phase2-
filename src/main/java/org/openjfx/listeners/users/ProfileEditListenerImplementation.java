package org.openjfx.listeners.users;

import org.openjfx.controller.mainMenuController.ProfileEditController;
import org.openjfx.event.ProfileEditEvent;

public class ProfileEditListenerImplementation implements ProfileEditListener{

    private ProfileEditController controller = new ProfileEditController();

    @Override
    public void listen(ProfileEditEvent event) {
        controller.change(event.getUser(), event.getFirstname(), event.getLastname(), event.getUsername(), event.getPassword(), event.getEmail(), event.getBio(), event.getPhoneNumber(), event.getBirthday());
    }

}