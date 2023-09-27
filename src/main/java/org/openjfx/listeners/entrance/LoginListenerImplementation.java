package org.openjfx.listeners.entrance;

import org.openjfx.controller.registrationController.LoginController;
import org.openjfx.event.LoginEvent;

public class LoginListenerImplementation implements LoginListener {

    private LoginController loginController = new LoginController();

    @Override
    public void listen(LoginEvent event) {
        loginController.login(event.getUsername(), event.getPassword());
    }

    public LoginController getLoginController() {
        return loginController;
    }
}
